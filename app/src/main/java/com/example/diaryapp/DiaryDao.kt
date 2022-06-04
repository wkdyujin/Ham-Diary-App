package com.example.diaryapp

import androidx.room.*
import androidx.room.Dao

@Dao
interface DiaryDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insert(diary: DiaryTable)

    @Update
    fun update(diary: DiaryTable)

    @Delete
    fun delete(diary: DiaryTable)

    @Query("SELECT * FROM DiaryTable")
    fun selectAll(): MutableList<DiaryTable>
}