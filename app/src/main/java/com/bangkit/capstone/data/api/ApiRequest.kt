package com.bangkit.capstone.data.api

import retrofit2.http.GET

interface ApiRequest {

    @GET("/getnews")
    suspend fun getNews(): NewsApiJSON
}