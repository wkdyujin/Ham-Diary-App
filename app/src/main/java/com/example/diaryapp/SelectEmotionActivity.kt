package com.example.diaryapp

import android.R
import android.content.Intent
import android.graphics.Bitmap
import android.graphics.BitmapFactory
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.example.diaryapp.databinding.ActivitySelectEmotionBinding
import java.io.ByteArrayOutputStream


class SelectEmotionActivity : AppCompatActivity() {
    lateinit var binding: ActivitySelectEmotionBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        binding = ActivitySelectEmotionBinding.inflate(layoutInflater)
        super.onCreate(savedInstanceState)
        setContentView(binding.root)

        val selectedDate = intent.getStringExtra("selectedDate")

        val year = intent.getStringExtra("year")
        val month = intent.getStringExtra("month")
        val day = intent.getStringExtra("day")
        val week = intent.getStringExtra("week")
        binding.selectEmotionYearTv.text = "${year}년 ${month}월 ${day}일"

        val intent = Intent(this, WritingDiaryActivity::class.java)

        binding.selectEmotionBtn.setOnClickListener {
            intent.putExtra("selectedDate", selectedDate)
            intent.putExtra("year", year)
            intent.putExtra("month", month)
            intent.putExtra("day", day)
            intent.putExtra("week", week)
            startActivity(intent)
        }


        binding.emotionHappyIb.setOnClickListener {
            binding.selectEmotionBtn.isEnabled = true
            intent.putExtra("emotion", "happy")
        }
        binding.emotionExcitedIb.setOnClickListener {
            binding.selectEmotionBtn.isEnabled = true
            intent.putExtra("emotion", "excited")
        }
        binding.emotionProudIb.setOnClickListener {
            binding.selectEmotionBtn.isEnabled = true
            intent.putExtra("emotion", "proud")
        }
        binding.emotionFineIb.setOnClickListener {
            binding.selectEmotionBtn.isEnabled = true
            intent.putExtra("emotion", "fine")
        }
        binding.emotionStressIb.setOnClickListener {
            binding.selectEmotionBtn.isEnabled = true
            intent.putExtra("emotion", "stress")
        }
        binding.emotionWorriedIb.setOnClickListener {
            binding.selectEmotionBtn.isEnabled = true
            intent.putExtra("emotion", "worried")
        }
        binding.emotionSadIb.setOnClickListener {
            binding.selectEmotionBtn.isEnabled = true
            intent.putExtra("emotion", "sad")
        }
        binding.emotionTiredIb.setOnClickListener {
            binding.selectEmotionBtn.isEnabled = true
            intent.putExtra("emotion", "tired")
        }
    }
}