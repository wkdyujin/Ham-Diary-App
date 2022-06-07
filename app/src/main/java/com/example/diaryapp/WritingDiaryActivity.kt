package com.example.diaryapp

import android.content.Intent
import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import androidx.appcompat.app.AppCompatActivity
import com.example.diaryapp.databinding.ActivityWritingDiaryBinding
import kotlinx.coroutines.*
import org.w3c.dom.Entity

class WritingDiaryActivity: AppCompatActivity() {

    lateinit var binding: ActivityWritingDiaryBinding
//    private val OPEN_GALLERY = 1
//    val db = DiaryDatabase.getDatabase(applicationContext) // 얘가 문제였다 (왜?)
    val diaryDB = DiaryDatabase

    override fun onCreate(savedInstanceState: Bundle?) {
        binding = ActivityWritingDiaryBinding.inflate(layoutInflater)
        super.onCreate(savedInstanceState)
        setContentView(binding.root)

        val diaryDB = DiaryDatabase.getDatabase(applicationContext)

        val year = intent.getStringExtra("year")
        val month = intent.getStringExtra("month")
        val day = intent.getStringExtra("day")
        val week = intent.getStringExtra("week")
        binding.writingDiaryDateTv.text = "${year}년 ${month}월 ${day}일"

        binding.writingDiaryDayOfWeekTv.text = "${week}"

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



////        // db에 일기 추가
//        binding.writingDiarySubmitBtn.setOnClickListener {
//            CoroutineScope(Dispatchers.Default).launch {
//                diaryDB?.DiaryDao()?.insert(DiaryTable(year.toString().toInt(),
//                    month.toString().toInt(), day.toString().toInt(), week.toString().toInt()))
//            }
//        }

//        binding.writingDiarySubmitBtn.setOnClickListener {
//            val diary = DiaryTable(
//                year.toString().toInt(),
//                month.toString().toInt(),
//                day.toString().toInt(),
//                week.toString()
//            )
//            DiaryDatabase.getDatabase(this)!!.DiaryDao().insert(diary)
//            startActivity(Intent(this, MainActivity::class.java))
//        }

//        binding.writingDiarySubmitBtn.setOnClickListener {
//            val diary = DiaryTable(
//                year.toString().toInt(), month.toString().toInt(), day.toString().toInt(), week.toString()
//            )
//            db!!.DiaryDao().insert(diary)
//        }

//        // 갤러리에서 사진 추가 (오류)
//        binding.writingDiaryAddImgBtn.setOnClickListener{
//            openGallery()
//        }
    }

//    private fun openGallery() {
//        val intent = Intent(Intent.ACTION_GET_CONTENT)
//        intent.type = "image/*"
//        startActivityForResult(intent, OPEN_GALLERY)
//    }
//
//    @Override
//    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
//        super.onActivityResult(requestCode, resultCode, data)
//
//        if(requestCode == RESULT_OK) { //여기 안 됨
//            if(requestCode == OPEN_GALLERY) {
//                var currentImageUrl: Uri? = data?.data
//                try {
//                    val bitmap = MediaStore.Images.Media.getBitmap(contentResolver, currentImageUrl)
//                    binding.writingDiaryImgIv.visibility = View.VISIBLE
//                    binding.writingDiaryImgIv.setImageBitmap(bitmap)
//                } catch (e:Exception) {
//                    e.printStackTrace()
//                }
//            }
//        } else {
//            Log.d ("ActivityResult", "something wrong")
//        }
//    }
}