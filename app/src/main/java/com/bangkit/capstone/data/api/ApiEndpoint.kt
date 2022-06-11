package com.bangkit.capstone.data.api

import retrofit2.Call
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.POST

interface ApiEndpoint {

    @GET("getnews")
    fun getNews(): Call<NewsApiJSON>

    @POST("predict")
    fun sendPredict(@Body dataModal: PredictData): Call<PredictResponses>
}