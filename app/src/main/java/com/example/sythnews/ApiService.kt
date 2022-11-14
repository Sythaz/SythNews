package com.example.sythnews

import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Query

interface ApiService {
    @GET("v2/top-headlines")
    fun getNews(
        @Query("country")
        countryCode: String = "id",
        @Query("page")
        pageNumber: Int = 1,
        @Query("apiKey")
        apiKey: String = "0a924b28a2754d51848582999d7c62fd"
    ) : Call<Response>
}