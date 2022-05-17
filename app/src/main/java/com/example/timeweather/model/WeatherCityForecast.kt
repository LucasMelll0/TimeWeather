package com.example.timeweather.model

import com.example.timeweather.model.jsonClasses.Main

class WeatherCityForecast(
    val name: String,
    val firstDay: DayForecast,
    val secondDay: DayForecast,
    val thirtDay : DayForecast,
    val forthDay: DayForecast,
    val fifthDay : DayForecast)