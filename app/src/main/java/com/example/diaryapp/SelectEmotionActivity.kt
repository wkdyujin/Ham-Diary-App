package com.example.diaryapp

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.example.diaryapp.databinding.ActivitySelectEmotionBinding

class SelectEmotionActivity : AppCompatActivity() {
    lateinit var binding: ActivitySelectEmotionBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        val binding = ActivitySelectEmotionBinding.inflate(layoutInflater)
        super.onCreate(savedInstanceState)
        setContentView(binding.root)

        val year = intent.getStringExtra("year")
        val month = intent.getStringExtra("month")
        val day = intent.getStringExtra("day")
        val week = intent.getStringExtra("week")
        binding.selectEmotionYearTv.text = "${year}년 ${month}월 ${day}일"

        binding.selectEmotionBtn.setOnClickListener {
            val intent = Intent(this, WritingDiaryActivity::class.java)
            intent.putExtra("year", year)
            intent.putExtra("month", month)
            intent.putExtra("day", day)
            intent.putExtra("week", week)
            startActivity(intent)
        }
    }
}