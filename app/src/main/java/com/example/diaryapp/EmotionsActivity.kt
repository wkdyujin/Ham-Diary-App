package com.example.diaryapp

import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.diaryapp.databinding.ActivityEmotionsBinding
import kotlinx.coroutines.*
import java.text.SimpleDateFormat
import java.util.*

class EmotionsActivity: AppCompatActivity() {
    lateinit var binding: ActivityEmotionsBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        binding = ActivityEmotionsBinding.inflate(layoutInflater)
        super.onCreate(savedInstanceState)
        setContentView(binding.root)

        val diaryDB = DiaryDatabase.getDatabase(applicationContext)

        // adapter와 rv를 연결한다
        val adapter = EmotionRVAdapter(mutableListOf())
        binding.rv.layoutManager = LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false)

        CoroutineScope(Dispatchers.Main).launch {
            // DB에서 데이터를 모두 가져와서 list에 넣는다
            val getList = CoroutineScope(Dispatchers.IO).async {
                diaryDB!!.DiaryDao().getDiarys()
            }.await()
            withContext(Dispatchers.Main){
                // list와 adapter를 연결한다.
                adapter.setList(getList)
                binding.rv.adapter = adapter
            }
        }

        // 리사이클러뷰 아이템을 클릭하면 해당 날짜의 일기를 보여준다.
        adapter.setItemClickListener(object: EmotionRVAdapter.OnItemClickListener{
            override fun onClick(v: View, position: Int) {
                val date = adapter.getElement(position).date
                val diary = adapter.getElement(position).diary
                intent = Intent(this@EmotionsActivity, ReadDiaryActivity::class.java)
                intent.putExtra("selectedDate", date.toString())
                intent.putExtra("content", diary)
                startActivity(intent)
            }
        })

    }
}