package com.example.timeweather.model

class CityDAO {

    companion object {
        @JvmStatic
        var cities = ArrayList<City>()
    }

    public fun save(city : City) {
        cities.add(city)
    }

    public fun all(): ArrayList<City> {
        return ArrayList<City>(cities)
    }

    fun saveALl(all: java.util.ArrayList<City>) {
        cities.addAll(all)
    }
}