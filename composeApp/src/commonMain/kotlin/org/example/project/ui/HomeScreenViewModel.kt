package org.example.project.ui

import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.IO
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import org.example.project.data.models.WeatherResponse
import org.example.project.data.repository.WeatherRepository

class HomeScreenViewModel {

    val repository= WeatherRepository()
    private val _state = MutableStateFlow<HomeScreenState>(HomeScreenState.Loading)
    val state = _state.asStateFlow()
            fun fetchWeather(city: String) {
                CoroutineScope(Dispatchers.IO).launch {
                    _state.value = HomeScreenState.Loading
                    try {
                        val response = repository.fetchWeather(city)
                        _state.value = HomeScreenState.Success(response)

                    } catch (e: Exception) {
                        _state.value = HomeScreenState.Error(e.message ?: "An error occurred")

                    }
                }
            }





}

sealed class HomeScreenState {
    object Loading : HomeScreenState()
    data class Success(val data: WeatherResponse): HomeScreenState()
    data class Error(val message: String): HomeScreenState()    }
