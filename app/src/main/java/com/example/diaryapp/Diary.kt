package com.example.diaryapp

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
data class Diary(
    @PrimaryKey var year: Int?,
    var month: Int?,
    var day: Int?,
    var dayOfWeek: Int?,
    var content: String?
)
