package com.example.diaryapp

import androidx.room.*
import androidx.room.Dao

@Dao
interface DiaryDao {
    @Insert
    fun insert(diary: DiaryTable)

    @Update
    fun update(diary: DiaryTable)

    @Delete
    fun delete(diary: DiaryTable)

    @Query("SELECT * FROM DiaryTable")
    fun getDiarys(): MutableList<DiaryTable>

    @Query("SELECT diary FROM DiaryTable WHERE date = :date")
    fun getDiaryContent(date: Int): String

    @Query("SELECT year FROM DiaryTable WHERE date = :date")
    fun getYear(date: Int): Int

    @Query("SELECT month FROM DiaryTable WHERE date = :date")
    fun getMonth(date: Int): Int

    @Query("SELECT day FROM DiaryTable WHERE date = :date")
    fun getDay(date: Int): Int

    @Query("SELECT dayOfWeek FROM DiaryTable WHERE date = :date")
    fun getDayOfWeek(date: Int): String
}