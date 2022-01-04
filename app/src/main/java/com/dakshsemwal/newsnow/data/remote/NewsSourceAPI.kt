package com.dakshsemwal.newsnow.data.remote

import com.dakshsemwal.newsnow.data.remote.dto.NewsListDTO
import retrofit2.http.GET
import retrofit2.http.Query

interface NewsSourceAPI {

    @GET("/v2/top-headlines")
    suspend fun getTrendingNews(
        @Query("apiKey") apiKey: String,
        @Query("country") country: String
    ): NewsListDTO

}