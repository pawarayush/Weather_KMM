package org.example.project.data.models
import kotlinx.serialization.Serializable

@Serializable

data class Coord(
    val lat: Double?,
    val lon: Double?
)