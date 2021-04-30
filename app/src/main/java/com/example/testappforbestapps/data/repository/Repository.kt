package com.example.testappforbestapps.data.repository

import android.util.Log
import androidx.lifecycle.LiveData
import com.example.testappforbestapps.data.db.NewsDao
import com.example.testappforbestapps.data.db.Entityes.NewsItem


class Repository(private val dao: NewsDao) {

    val allNews: LiveData<List<NewsItem>> = dao.getAllNews()

    suspend fun insert(newsItem: NewsItem){
        dao.insert(newsItem)

    }
}