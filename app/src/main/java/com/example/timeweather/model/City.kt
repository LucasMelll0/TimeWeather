package com.example.timeweather.model

import java.io.Serializable

class City(
    val nome: String,
    val hora: String,
    val clima: String,
    val temperatura: Double,
    val temp_min: Double,
    val temp_max: Double,
    val lat: String,
    val lon : String) : Serializable