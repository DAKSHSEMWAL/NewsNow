package com.dakshsemwal.newsnow.domain.repository

import com.dakshsemwal.newsnow.data.remote.dto.NewsListDTO

interface NewsRepository {
    suspend fun getNews(country:String):NewsListDTO
}