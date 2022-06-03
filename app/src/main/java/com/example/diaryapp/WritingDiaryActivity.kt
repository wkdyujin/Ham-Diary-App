package com.example.diaryapp

import android.Manifest
import android.app.Activity
import android.content.Intent
import android.content.pm.PackageManager
import android.net.Uri
import android.os.Bundle
import android.provider.MediaStore
import android.text.Editable
import android.text.TextWatcher
import android.util.Log
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import androidx.core.app.ActivityCompat
import androidx.core.content.ContextCompat
import com.example.diaryapp.databinding.ActivityWritingDiaryBinding
import java.lang.Exception

class WritingDiaryActivity: AppCompatActivity() {

    lateinit var binding: ActivityWritingDiaryBinding
    private val OPEN_GALLERY = 1

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

        // 갤러리에서 사진 추가 (오류)
        binding.writingDiaryAddImgBtn.setOnClickListener{
            openGallery()
        }
    }

    private fun openGallery() {
        val intent = Intent(Intent.ACTION_GET_CONTENT)
        intent.type = "image/*"
        startActivityForResult(intent, OPEN_GALLERY)
    }

    @Override
    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)

        if(requestCode == RESULT_OK) { //여기 안 됨
            if(requestCode == OPEN_GALLERY) {
                var currentImageUrl: Uri? = data?.data
                try {
                    val bitmap = MediaStore.Images.Media.getBitmap(contentResolver, currentImageUrl)
                    binding.writingDiaryImgIv.visibility = View.VISIBLE
                    binding.writingDiaryImgIv.setImageBitmap(bitmap)
                } catch (e:Exception) {
                    e.printStackTrace()
                }
            }
        } else {
            Log.d ("ActivityResult", "something wrong")
        }
    }
}