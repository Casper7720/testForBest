package com.example.testappforbestapps.data.JsonData

import android.graphics.Bitmap
import android.graphics.BitmapFactory
import android.util.Log
import androidx.activity.viewModels
import com.example.testappforbestapps.data.db.Entityes.NewsItem
import com.example.testappforbestapps.other.AppApplication
import com.example.testappforbestapps.other.AppViewModel
import com.example.testappforbestapps.other.AppViewModelFactory
import java.io.ByteArrayOutputStream
import java.io.InputStream
import java.net.HttpURLConnection
import java.net.URL

object Common {

    private val BASE_URL = "https://saurav.tech"
    val jsonComand: GsonComand
        get() = RetrofitClient.getClient(BASE_URL).create(GsonComand::class.java)

    fun toDataBase(): MutableList<NewsItem> {

        var list: MutableList<NewsItem> = mutableListOf()
        for (item in jsonComand.getData().execute().body()?.articles!!) {

            var byteArrayImage = downloadImageFromPath(item?.urlToImage)
            Log.i("title", item?.title.toString())
            var newsItem = NewsItem(
                item?.title,
                item?.description,
                item?.publishedAt,
                item?.content,
                byteArrayImage
            )
            list.add(newsItem)
        }
        return list
    }

    private fun downloadImageFromPath(path: String?): ByteArray? {
        var `in`: InputStream? = null
        var bmp: Bitmap? = null
        var responseCode = -1
        try {
            val url = URL(path)
            val con: HttpURLConnection = url.openConnection() as HttpURLConnection
            con.setDoInput(true)
            con.connect()
            responseCode = con.getResponseCode()
            if (responseCode == HttpURLConnection.HTTP_OK) {
                //download
                `in` = con.getInputStream()
                bmp = BitmapFactory.decodeStream(`in`)

                val stream = ByteArrayOutputStream()
                bmp.compress(Bitmap.CompressFormat.PNG, 100, stream)
                val byteArray: ByteArray = stream.toByteArray()
                bmp.recycle()
                `in`.close()

                return byteArray
            }
        } catch (ex: Exception) {
            Log.e("Exception", ex.toString())
        }
        return null
    }
}