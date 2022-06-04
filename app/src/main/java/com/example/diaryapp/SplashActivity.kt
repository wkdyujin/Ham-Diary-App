package com.example.diaryapp

import android.content.Intent
import android.os.Bundle
import android.os.Handler
import android.os.Looper
import androidx.appcompat.app.AppCompatActivity
import com.example.diaryapp.databinding.ActivitySplashBinding

class SplashActivity: AppCompatActivity() {
    lateinit var binding: ActivitySplashBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivitySplashBinding.inflate(layoutInflater)
        setContentView(binding.root)

        // 핸들러
        val handler = Handler(Looper.getMainLooper())
        // postDelayed 사용하여 1초 뒤에 메인 화면이 나오게 설정
        handler.postDelayed({
            startActivity(Intent(this, MainActivity::class.java))
        }, 1000)
    }
}