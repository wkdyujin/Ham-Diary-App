package com.example.diaryapp

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.diaryapp.databinding.ActivityEmotionsBinding

class EmotionsActivity: AppCompatActivity() {
    lateinit var binding: ActivityEmotionsBinding
    private var diary = ArrayList<Diary>()

    override fun onCreate(savedInstanceState: Bundle?) {
        binding = ActivityEmotionsBinding.inflate(layoutInflater)
        super.onCreate(savedInstanceState)
        setContentView(binding.root)

        diary.apply {
            add(Diary(6, 1, "수요일", "행복해요"))
            add(Diary(6, 9, "목요일", "뿌듯해요"))
            add(Diary(6, 10, "금요일", "행복해요"))
            add(Diary(6, 11, "토요일", "화나요"))
            add(Diary(6, 12, "일요일", "슬퍼요"))
            add(Diary(6, 13, "월요일", "행복해요"))
        }

        // Adapter와 data List 연결
        val emotionRVAdapter = EmotionRVAdapter(diary)
        binding.rv.adapter = emotionRVAdapter
        binding.rv.layoutManager = LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false)
    }
}