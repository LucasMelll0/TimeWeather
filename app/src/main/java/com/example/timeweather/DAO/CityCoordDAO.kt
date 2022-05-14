package com.example.timeweather.DAO

import com.example.timeweather.model.CityCoord

class CityCoordDAO {

    companion object{
        @JvmStatic
        var citiesCoords = ArrayList<CityCoord>()
    }

    public fun save(cityCoord : CityCoord){
        citiesCoords.add(cityCoord)
    }

    public fun all(): ArrayList<CityCoord> {
        return ArrayList<CityCoord>(citiesCoords)
    }
}