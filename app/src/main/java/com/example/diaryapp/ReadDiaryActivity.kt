package com.example.diaryapp

import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.widget.Toast
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

        binding.readingDiaryContentEt.text = intent.getStringExtra("content")
        val date = intent.getStringExtra("selectedDate")!!.toInt()

        val diaryDB = DiaryDatabase.getDatabase(applicationContext)!!
        CoroutineScope(Dispatchers.IO).launch { // launch: 리턴 값 없음
            val year = diaryDB!!.DiaryDao().getYear(date)
            val month = diaryDB!!.DiaryDao().getMonth(date)
            val day = diaryDB!!.DiaryDao().getDay(date)
            val content = diaryDB!!.DiaryDao().getDiaryContent(date)
            val emotion = diaryDB!!.DiaryDao().getEmotion(date)
            val dayOfWeek = diaryDB!!.DiaryDao().getDayOfWeek(date)

            CoroutineScope(Dispatchers.Main).launch {
                binding.readingDiaryDateTv.text = "${year}년 ${month}월 ${day}일"
                binding.readingDiaryDayOfWeekTv.text = dayOfWeek
                binding.readingDiaryContentEt.text = content
                binding.readingDiaryEmotionIv.setImageResource(setEmotion(emotion))
            }
        }

        // 텍스트 누르면 글 수정
        binding.readingDiaryContentEt.setOnClickListener {
            CoroutineScope(Dispatchers.IO).launch {
                val content = diaryDB!!.DiaryDao().getDiaryContent(date)
                val intent = Intent(this@ReadDiaryActivity, WritingDiaryActivity::class.java)
                intent.putExtra("diaryContent", content)
                startActivity(intent)
            }
        }

        // 삭제
        binding.readingDiaryContentDelBtn.setOnClickListener{
            Toast.makeText(this, "${binding.readingDiaryDateTv.text} 일기를 삭제했어요", Toast.LENGTH_LONG).show()
            CoroutineScope(Dispatchers.IO).launch {
                diaryDB!!.DiaryDao().deleteByDate(date)
                startActivity(Intent(this@ReadDiaryActivity, MainActivity::class.java))
            }
        }
    }


    private fun setEmotion(emotion_name: String): Int {
        if(emotion_name == "행복해요") {
            binding.readingDiaryEmtionNameTv.text = "행복해요"
            return R.drawable.emotion_happy_img
        }
        if(emotion_name == "즐거워요") {
            binding.readingDiaryEmtionNameTv.text = "즐거워요"
            return R.drawable.emotion_excited_img
        }
        if(emotion_name == "뿌듯해요") {
            binding.readingDiaryEmtionNameTv.text = "뿌듯해요"
            return R.drawable.emotion_proud_img
        }
        if(emotion_name == "괜찮아요") {
            binding.readingDiaryEmtionNameTv.text = "괜찮아요"
            return R.drawable.emotion_fine_img
        }
        if(emotion_name == "화나요") {
            binding.readingDiaryEmtionNameTv.text = "화나요"
            return R.drawable.emotion_stress_img
        }
        if(emotion_name == "걱정돼요") {
            binding.readingDiaryEmtionNameTv.text = "걱정돼요"
            return R.drawable.emotion_worried_img
        }
        if(emotion_name == "슬퍼요") {
            binding.readingDiaryEmtionNameTv.text = "슬퍼요"
            return R.drawable.emotion_sad_img
        }
        if(emotion_name == "피곤해요") {
            binding.readingDiaryEmtionNameTv.text = "피곤해요"
            return R.drawable.emotion_tired_img
        }
        return -1
    }
}
