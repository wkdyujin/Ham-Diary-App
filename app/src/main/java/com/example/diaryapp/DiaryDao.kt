package com.example.diaryapp

import androidx.room.*
import androidx.room.Dao

@Dao
interface DiaryDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insert(diary: Diary)

    @Update
    fun update(diary: Diary)

    @Delete
    fun delete(diary: Diary)

    @Query("SELECT * FROM Diary")
    fun selectAll(): MutableList<Diary>
}