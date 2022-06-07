package com.example.diaryapp

import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import androidx.room.Database
import com.example.diaryapp.databinding.ActivityMainBinding
import kotlinx.coroutines.*
import java.lang.Exception
import java.text.SimpleDateFormat
import java.time.LocalDate
import java.util.*

class MainActivity : AppCompatActivity() {
    lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        binding = ActivityMainBinding.inflate(layoutInflater)
        super.onCreate(savedInstanceState)
        setContentView(binding.root)

        val diaryDB = DiaryDatabase.getDatabase(applicationContext)!!

        // 더미 데이터 추가
        CoroutineScope(Dispatchers.IO).launch { //launch: 결과 반환 X
            val diarys = diaryDB!!.DiaryDao().getDiarys()
            if (diarys.isEmpty()) {
                diaryDB!!.DiaryDao().insert(DiaryTable(
                    20220606,
                    2022,
                    6,
                    6,
                    "월요일",
                    "앱 개발은 어려운 거구나..."
                ))

                diaryDB!!.DiaryDao().insert(DiaryTable(
                    20220605,
                    2022,
                    6,
                    5,
                    "일요일",
                    "room이랑 코루틴 어렵다. 햄스터 그림 귀엽게 그려놨는데 쓸 수 있겠지? 엉..엉.."
                ))
            }
        }

        binding.mainCalendar.setOnDateChangeListener { calendarView, year, month, day ->
            val date = Date(year.toString(), (month+1).toString(), day.toString())
            val intent = Intent(this, SelectEmotionActivity::class.java)
            intent.putExtra("year", date.year)
            intent.putExtra("month", date.month)
            intent.putExtra("day", date.day)

            // 선택된 날짜의 요일 가져오기
            if(date.month.toInt() < 10){
                date.month = "0" + date.month
            }
            if(date.day.toInt() < 10){
                date.day = "0" + date.day
            }
            // 선택된 요일 (PK)
            val selectedDate = date.year + date.month + date.day

            var diaryContent: String?
            // 선택된 날짜에 이미 쓴 일기가 있다면 diaryContent에 그 내용을 넣는다
            CoroutineScope(Dispatchers.Default).launch {
                diaryContent = CoroutineScope(Dispatchers.IO).async {
                    diaryDB!!.DiaryDao().getDiaryContent(selectedDate.toInt())
                }.await()

                if (diaryContent != null) {
                    val intent = Intent(this@MainActivity, ReadDiaryActivity::class.java)
                    Log.d("tag", selectedDate.toInt().toString())
                    intent.putExtra("selectedDate", selectedDate)
                    intent.putExtra("content", binding.content.text)
                    startActivity(intent)
                }
                else {
                    Log.d("tag","설마 else문 실행 안되나? 으아아악 이게 출력되면 된 거다")
                    val dateformat = SimpleDateFormat("yyyyMMdd").parse(selectedDate)
                    val week: String = SimpleDateFormat("EEEE", Locale.KOREA).format(dateformat)
                    intent.putExtra("selectedDate", selectedDate?.toInt())
                    intent.putExtra("week", week)
                    startActivity(intent)
                }
            }


//            Log.d("tag", diaryContent) // --> 아무것도 출력되지 않는다. (스레드가 꼬여서?)
//
//            // 선택된 날짜에 일기가 없다 > 일기를 쓰는 액티비티
//            if (diaryContent.equals("")) {
//                val dateformat = SimpleDateFormat("yyyyMMdd").parse(selectedDate)
//                val week: String = SimpleDateFormat("EEEE", Locale.KOREA).format(dateformat)
//                intent.putExtra("date", selectedDate.toInt())
//                intent.putExtra("week", week)
//                startActivity(intent)
//            }
//            else { // 일기가 있다 > 일기를 보여주는 액티비티
//                val intent = Intent(this, ReadDiaryActivity::class.java)
//                intent.putExtra("date", selectedDate.toInt())
//                intent.putExtra("content", binding.content.text)
//                startActivity(intent)
//            }


        }

        binding.mainWritingBtn.setOnClickListener {
            val cal = Calendar.getInstance()
            val intent = Intent(this, SelectEmotionActivity::class.java)
            val year = cal.get(Calendar.YEAR).toString()
            var month = (cal.get(Calendar.MONTH)+1).toString()
            var day = cal.get(Calendar.DATE).toString()
            val week: String = SimpleDateFormat("EEEE", Locale.KOREA).format(System.currentTimeMillis())
            intent.putExtra("year", year)
            intent.putExtra("month", month)
            intent.putExtra("day", day)

            if(month.toInt() < 10){
                month = "0$month"
            }
            if(day.toInt() < 10){
                day = "0$day"
            }
            val selectedDate = year + month + day

            intent.putExtra("date", selectedDate)
            intent.putExtra("week", week)
            startActivity(intent)

            var diaryContent = ""
            // 선택된 날짜에 이미 쓴 일기가 있다면 diaryContent에 그 내용을 넣는다
            CoroutineScope(Dispatchers.Default).launch {
                diaryContent = CoroutineScope(Dispatchers.IO).async {
                    diaryDB!!.DiaryDao().getDiaryContent(selectedDate.toInt())
                }.await()

                Log.d("tag", diaryContent)
                if (diaryContent != "") {
                    val intent = Intent(this@MainActivity, ReadDiaryActivity::class.java)
                    Log.d("tag", selectedDate.toInt().toString())
                    intent.putExtra("selectedDate", selectedDate)
                    intent.putExtra("content", binding.content.text)
                    startActivity(intent)
                }
                else {
                    val dateformat = SimpleDateFormat("yyyyMMdd").parse(selectedDate)
                    val week: String = SimpleDateFormat("EEEE", Locale.KOREA).format(dateformat)
                    intent.putExtra("selectedDate", selectedDate?.toInt())
                    intent.putExtra("week", week)
                    startActivity(intent)
                }
            }
        }

    }
}