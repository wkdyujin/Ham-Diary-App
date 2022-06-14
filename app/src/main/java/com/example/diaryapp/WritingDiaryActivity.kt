package com.example.diaryapp

import android.annotation.SuppressLint
import android.content.Intent
import android.graphics.Bitmap
import android.graphics.BitmapFactory
import android.graphics.Color
import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.util.Log
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.example.diaryapp.databinding.ActivityWritingDiaryBinding
import kotlinx.coroutines.*
import org.w3c.dom.Entity

class WritingDiaryActivity: AppCompatActivity() {

    lateinit var binding: ActivityWritingDiaryBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        binding = ActivityWritingDiaryBinding.inflate(layoutInflater)
        super.onCreate(savedInstanceState)
        setContentView(binding.root)

        val diaryDB = DiaryDatabase.getDatabase(applicationContext)

        val selectedDate = intent.getStringExtra("selectedDate")
        val year = intent.getStringExtra("year")
        val month = intent.getStringExtra("month")
        val day = intent.getStringExtra("day")
        val week = intent.getStringExtra("week")
        val emotion = intent.getStringExtra("emotion")

        binding.writingDiaryDateTv.text = "${year}년 ${month}월 ${day}일"
        binding.writingDiaryDayOfWeekTv.text = "${week}"

        if (emotion != null) {
            setEmotion(emotion)
        }


        // 입력된 글자수 세기
        binding.writingDiaryContentEt.addTextChangedListener(object: TextWatcher{
            // 작성 전
            override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {
                binding.writingDiaryWordsNumTv.text = "글자 수: 0"
            }

            // 작성 중
            override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {
                var userinput = binding.writingDiaryContentEt.text.toString()
                binding.writingDiaryWordsNumTv.text = "글자 수: " + userinput.length.toString()
            }

            // 작성 후
            override fun afterTextChanged(s: Editable?) {
                var userinput = binding.writingDiaryContentEt.text.toString()
                binding.writingDiaryWordsNumTv.text = "글자 수: " + userinput.length.toString()
            }
        })

//        // db에 일기 추가
        binding.writingDiarySubmitBtn.setOnClickListener {
            val diary = DiaryTable(
                selectedDate!!.toInt(),
                year!!.toInt(),
                month!!.toInt(),
                day!!.toInt(),
                week.toString(),
                binding.writingDiaryContentEt.text.toString(),
                binding.writingDiaryEmtionNameTv.text.toString()
            )
            CoroutineScope(Dispatchers.IO).launch {
                diaryDB!!.DiaryDao().insert(diary)
            }
            Toast.makeText(this, "${binding.writingDiaryDateTv.text} 일기를 썼어요", Toast.LENGTH_LONG).show()
            startActivity(Intent(this, MainActivity::class.java))
        }
    }

    @SuppressLint("ResourceAsColor")
    fun setEmotion(emotion_name: String) {
        if(emotion_name == "happy") {
            binding.writingDiaryEmotionIv.setImageResource(R.drawable.emotion_happy_img)
            binding.writingDiaryEmtionNameTv.text = "행복해요"
            binding.writingDiaryEmtionNameTv.setTextColor(R.color.happy)
        }
        if(emotion_name == "excited") {
            binding.writingDiaryEmotionIv.setImageResource(R.drawable.emotion_excited_img)
            binding.writingDiaryEmtionNameTv.text = "즐거워요"
            binding.writingDiaryEmtionNameTv.setTextColor(R.color.excited)
        }
        if(emotion_name == "proud") {
            binding.writingDiaryEmotionIv.setImageResource(R.drawable.emotion_proud_img)
            binding.writingDiaryEmtionNameTv.text = "뿌듯해요"
            binding.writingDiaryEmtionNameTv.setTextColor(R.color.proud)
        }
        if(emotion_name == "fine") {
            binding.writingDiaryEmotionIv.setImageResource(R.drawable.emotion_fine_img)
            binding.writingDiaryEmtionNameTv.text = "괜찮아요"
            binding.writingDiaryEmtionNameTv.setTextColor(R.color.fine)
        }
        if(emotion_name == "stress") {
            binding.writingDiaryEmotionIv.setImageResource(R.drawable.emotion_stress_img)
            binding.writingDiaryEmtionNameTv.text = "화나요"
            binding.writingDiaryEmtionNameTv.setTextColor(R.color.stress)
        }
        if(emotion_name == "worried") {
            binding.writingDiaryEmotionIv.setImageResource(R.drawable.emotion_worried_img)
            binding.writingDiaryEmtionNameTv.text = "걱정돼요"
            binding.writingDiaryEmtionNameTv.setTextColor(R.color.worried)
        }
        if(emotion_name == "sad") {
            binding.writingDiaryEmotionIv.setImageResource(R.drawable.emotion_sad_img)
            binding.writingDiaryEmtionNameTv.text = "슬퍼요"
            binding.writingDiaryEmtionNameTv.setTextColor(R.color.sad)
        }
        if(emotion_name == "tired") {
            binding.writingDiaryEmotionIv.setImageResource(R.drawable.emotion_tired_img)
            binding.writingDiaryEmtionNameTv.text = "피곤해요"
            binding.writingDiaryEmtionNameTv.setTextColor(R.color.tired)
        }
    }
}