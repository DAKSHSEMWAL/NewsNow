package com.dakshsemwal.newsnow.domain.usecase

import com.dakshsemwal.newsnow.common.Resource
import com.dakshsemwal.newsnow.data.remote.dto.NewsListDTO
import com.dakshsemwal.newsnow.domain.repository.NewsRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import retrofit2.HttpException
import java.io.IOException
import javax.inject.Inject

class GetNewsCase @Inject constructor(private val repository: NewsRepository) {
    operator fun invoke(): Flow<Resource<NewsListDTO>> = flow {
        try {
            emit(Resource.Loading<NewsListDTO>())
            val movies = repository.getNews("us")
            emit(Resource.Success<NewsListDTO>(movies))
        } catch (e: HttpException) {
            emit(Resource.Error<NewsListDTO>(e.localizedMessage ?: "An unexpected error occurred"))
        } catch (e: IOException) {
            emit(Resource.Error<NewsListDTO>("Couldn't reach server.Check your internet connection "))
        }
    }
}

