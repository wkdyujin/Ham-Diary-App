package com.example.diaryapp

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.example.diaryapp.databinding.ActivityWritingDiaryBinding

class WritingDiaryActivity: AppCompatActivity() {
    lateinit var binding: ActivityWritingDiaryBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        val binding = ActivityWritingDiaryBinding.inflate(layoutInflater)
        super.onCreate(savedInstanceState)
        setContentView(binding.root)

        val year = intent.getStringExtra("year")
        val month = intent.getStringExtra("month")
        val day = intent.getStringExtra("day")
        val week = intent.getStringExtra("week")
        binding.writingDiaryDateTv.text = "${year}년 ${month}월 ${day}일"

        binding.writingDiaryDayOfWeekTv.text = "${week}"
    }
}