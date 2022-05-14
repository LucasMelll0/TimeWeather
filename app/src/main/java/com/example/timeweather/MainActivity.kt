package com.example.timeweather

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.timeweather.DAO.CityCoordDAO
import com.example.timeweather.DAO.CityDAO
import com.example.timeweather.model.CityCoord
import com.example.timeweather.requisition.Weather
import java.util.concurrent.ExecutorService
import java.util.concurrent.Executors

class MainActivity : AppCompatActivity() {



    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        val city1 = CityCoord("-21.0422", "-41.9733")
        val city2 = CityCoord("-21.205", "-41.8878")

        val cityCoordDAO = CityCoordDAO()
        cityCoordDAO.save(city1)
        cityCoordDAO.save(city2)
        val executor: ExecutorService = Executors.newSingleThreadExecutor()
        val tempoAtual = Weather(executor)
        tempoAtual.setCurrentWeather()
        while (CityDAO.cities.size == 0) {
            continue
        }

        val cityList = CityDAO.cities




        val recyclerViewCity : RecyclerView = findViewById(R.id.recyclerview_cities)

        recyclerViewCity.layoutManager = LinearLayoutManager(this,LinearLayoutManager.HORIZONTAL, false)
        recyclerViewCity.adapter = CityWeatherAdapter(cityList)


    }


    override fun onResume() {
        super.onResume()

    }




}
