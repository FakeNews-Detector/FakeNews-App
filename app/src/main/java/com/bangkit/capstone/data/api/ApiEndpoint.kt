package com.bangkit.capstone.data.api

import retrofit2.Call
import retrofit2.http.GET

interface ApiEndpoint {

    @GET("getnews")
    fun getNews(): Call<NewsApiJSON>
}