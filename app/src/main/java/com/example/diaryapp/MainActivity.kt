package com.example.diaryapp

import android.content.Intent
import android.os.Bundle
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import com.example.diaryapp.databinding.ActivityMainBinding
import java.text.SimpleDateFormat
import java.time.LocalDate
import java.util.*

class MainActivity : AppCompatActivity() {
    lateinit var binding: ActivityMainBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        val binding = ActivityMainBinding.inflate(layoutInflater)
        super.onCreate(savedInstanceState)
        setContentView(binding.root)

        binding.mainCalendar.setOnDateChangeListener { calendarView, year, month, day ->
            val intent = Intent(this, SelectEmotionActivity::class.java)
            val date = Date(year.toString(), (month+1).toString(), day.toString())
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
            val selectedDate = date.year + date.month + date.day
//            Log.d("tag", "선택한 날짜: ${selectedDate}")
            val dateformat = SimpleDateFormat("yyyyMMdd").parse(selectedDate)
            val week: String = SimpleDateFormat("EEEE", Locale.KOREA).format(dateformat)
//            Log.d("tag", "요일: ${week}")
            intent.putExtra("week", week)

            startActivity(intent)
        }

        binding.mainWritingBtn.setOnClickListener {
            val cal = Calendar.getInstance()
            val intent = Intent(this, SelectEmotionActivity::class.java)
            val year = cal.get(Calendar.YEAR).toString()
            val month = (cal.get(Calendar.MONTH)+1).toString()
            val day = cal.get(Calendar.DATE).toString()
            val week: String = SimpleDateFormat("EEEE", Locale.KOREA).format(System.currentTimeMillis())

            intent.putExtra("year", year)
            intent.putExtra("month", month)
            intent.putExtra("day", day)
            intent.putExtra("week", week)
            startActivity(intent)
        }

    }
}