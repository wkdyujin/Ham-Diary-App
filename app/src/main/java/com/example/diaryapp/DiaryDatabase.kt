package com.example.diaryapp

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase

@Database(entities = [DiaryTable::class], version = 6) //어떤 테이블이 데이터베이스에 들어갈지
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
                        "diary-database" //
                    ).fallbackToDestructiveMigration().build()
                }
            }
            return instance
        }
    }
}