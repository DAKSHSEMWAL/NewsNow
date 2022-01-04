package com.dakshsemwal.newsnow.data.remote

import com.dakshsemwal.newsnow.data.remote.dto.NewsListDTO
import retrofit2.http.GET
import retrofit2.http.Query

interface NewsSourceAPI {

    @GET("/top-headlines")
    suspend fun getTrendingNews(
        @Query("api_key") apiKey: String,
        @Query("country") country: String
    ): NewsListDTO

}