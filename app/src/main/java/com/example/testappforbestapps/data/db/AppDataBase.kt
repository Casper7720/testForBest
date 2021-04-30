package com.example.testappforbestapps.data.db

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.example.testappforbestapps.data.db.Entityes.NewsItem

@Database(
    entities = [NewsItem::class],
    version = 1
)
abstract class AppDataBase: RoomDatabase() {
    abstract fun getDao(): NewsDao
    companion object {
        @Volatile
        private var INSTANCE: AppDataBase? = null

        fun getDatabase(context: Context): AppDataBase {
            return INSTANCE ?: synchronized(this) {
                val instance = Room.databaseBuilder(
                    context.applicationContext,
                    AppDataBase::class.java,
                    "word_database"
                ).build()
                INSTANCE = instance
                instance
            }
        }
    }
}