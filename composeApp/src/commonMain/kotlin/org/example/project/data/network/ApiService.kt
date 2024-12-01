package org.example.project.data.network

import io.ktor.client.HttpClient
import io.ktor.client.call.body
import io.ktor.client.plugins.contentnegotiation.ContentNegotiation
import io.ktor.client.request.get
import io.ktor.serialization.kotlinx.json.json
import kotlinx.serialization.json.Json
import org.example.project.data.models.WeatherResponse

class ApiService {
    private val baseURL = "https://api.openweathermap.org/data/2.5/weather?q="
    private val httpClient = HttpClient {
       install(ContentNegotiation)  {
           json(Json{
               encodeDefaults = true
               isLenient = true
               coerceInputValues = true
               ignoreUnknownKeys = true

           })
       }
    }

    suspend fun getWeather(city: String): WeatherResponse {
        return httpClient.get("$baseURL$city&appid=05233160060d9bc832af01950755c64c&units=metric").body()
    }

}