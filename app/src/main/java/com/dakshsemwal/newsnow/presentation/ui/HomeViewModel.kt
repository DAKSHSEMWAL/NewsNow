package com.dakshsemwal.newsnow.presentation.ui

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.dakshsemwal.newsnow.common.Resource
import com.dakshsemwal.newsnow.domain.usecase.GetNewsCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import javax.inject.Inject

@HiltViewModel
class HomeViewModel @Inject constructor(
    private val getMovieUseCase: GetNewsCase
) : ViewModel() {

    private val _state = MutableLiveData(NewsState())
    val state: LiveData<NewsState> = _state

    init {
        getNews()
    }

    fun getNews() {
        getMovieUseCase().onEach { result ->
            when (result) {
                is Resource.Success -> {
                    _state.value = NewsState(newsListDTO = result.data)
                }
                is Resource.Error -> {
                    _state.value = NewsState(
                        error = result.message ?: "An unexpected error occurred"
                    )
                }
                is Resource.Loading -> {
                    _state.value = NewsState(isLoading = true)
                }
            }
        }.launchIn(viewModelScope)
    }
}