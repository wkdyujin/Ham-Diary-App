package com.example.diaryapp

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase

@Database(entities = [DiaryTable::class], version = 1) //어떤 테이블이 데이터베이스에 들어갈지
abstract class DiaryDatabase: RoomDatabase() {
    abstract fun DiaryDao(): DiaryDao

    companion object {
        private var instance: DiaryDatabase? = null

        @Synchronized
        fun getDatabase(context: Context): DiaryDatabase? {
            if (instance == null) {
                synchronized(DiaryDatabase::class) {
                    instance = Room.databaseBuilder(
                        context.applicationContext,
                        DiaryDatabase::class.java,
                        "diary-database" // 다른 DB랑 이름 겹치면 안 됨
                    ).build()
                }
            }
            return instance
        }
    }
}