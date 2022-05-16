package com.example.timeweather

import android.app.Application
import com.example.timeweather.DAO.CityCoordDAO
import com.example.timeweather.model.CityCoord

class MyApplication : Application() {

    override fun onCreate() {
        super.onCreate()
        val city1 = CityCoord("-21.0422", "-41.9733")
        val city2 = CityCoord("-21.205", "-41.8878")
        val cityCoordDAO = CityCoordDAO()
        cityCoordDAO.save(city1)
        cityCoordDAO.save(city2)
    }
}