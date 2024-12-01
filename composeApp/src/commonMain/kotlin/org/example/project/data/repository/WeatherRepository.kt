package org.example.project.data.repository

import org.example.project.data.network.ApiService


class WeatherRepository{

    private val apiService = ApiService()
    suspend fun fetchWeather(city: String) = apiService.getWeather(city)



}