package com.example.diaryapp

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
data class DiaryTable(
    @PrimaryKey val year: Int,
    val month: Int,
    val day: Int,
    val dayOfWeek: String
)