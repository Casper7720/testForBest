package com.example.testappforbestapps.data.db

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.example.testappforbestapps.data.db.Entityes.NewsItem

@Dao
interface NewsDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insert(news: NewsItem)

    @Query("SELECT * FROM newsItem")
    fun getAllNews(): LiveData<List<NewsItem>>
}