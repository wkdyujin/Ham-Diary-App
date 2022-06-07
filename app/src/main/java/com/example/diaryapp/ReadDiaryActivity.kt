package com.example.diaryapp

import android.content.Intent
import android.os.Bundle
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import com.example.diaryapp.databinding.ActivityReadDiaryBinding
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.async
import kotlinx.coroutines.launch

class ReadDiaryActivity : AppCompatActivity() {
    lateinit var binding: ActivityReadDiaryBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityReadDiaryBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.writingDiaryContentEt.text = intent.getStringExtra("content")
        val date = intent.getStringExtra("selectedDate")!!.toInt()

        val diaryDB = DiaryDatabase.getDatabase(applicationContext)!!
        CoroutineScope(Dispatchers.Default).launch { // launch: 리턴 값 없음
            val diaryContent = CoroutineScope(Dispatchers.IO).async { // IO: 디비 접근, async: 리턴 값 있음
                val year = diaryDB!!.DiaryDao().getYear(date)
                val month = diaryDB!!.DiaryDao().getMonth(date)
                val day = diaryDB!!.DiaryDao().getDay(date)
                val content = diaryDB!!.DiaryDao().getDiaryContent(date)
                binding.writingDiaryDateTv.text = "${year}년 ${month}월 ${day}일"
                val dayOfWeek = diaryDB!!.DiaryDao().getDayOfWeek(date)
                binding.writingDiaryDayOfWeekTv.text = dayOfWeek
                binding.writingDiaryContentEt.text = content
            }.await()

            // 텍스트 누르면 글 수정
            binding.writingDiaryContentEt.setOnClickListener {
                val intent = Intent(this@ReadDiaryActivity, WritingDiaryActivity::class.java)
                intent.putExtra("diaryContent", diaryContent.toString())
                startActivity(intent)
            }
        }

    }
}
