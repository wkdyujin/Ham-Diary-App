package com.example.diaryapp

import androidx.room.Entity
import androidx.room.PrimaryKey
import java.time.Month

@Entity
data class DiaryTable(
    @PrimaryKey val date: Int,
    val year: Int,
    val month: Int,
    val day: Int,
    val dayOfWeek: String,
    val diary: String,
    var emotionImg: Int? = null
)