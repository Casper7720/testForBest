package com.example.testappforbestapps.data.JsonData

import com.example.testappforbestapps.data.Response
import retrofit2.Call
import retrofit2.http.GET

interface GsonComand {

    @GET("/NewsAPI/top-headlines/category/science/in.json")
    fun getData(): Call<Response>
}