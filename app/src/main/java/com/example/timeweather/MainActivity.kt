package com.example.timeweather

import android.opengl.Visibility
import android.os.Bundle
import android.view.View
import android.widget.ProgressBar
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.isGone
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.timeweather.DAO.CityCoordDAO
import com.example.timeweather.DAO.CityDAO
import com.example.timeweather.model.City
import com.example.timeweather.model.CityCoord
import com.example.timeweather.requisition.Weather
import java.util.concurrent.ExecutorService
import java.util.concurrent.Executors

class MainActivity : AppCompatActivity() {

    private lateinit var cityList : ArrayList<City>



    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        val city1 = CityCoord("-21.0422", "-41.9733")
        val city2 = CityCoord("-21.205", "-41.8878")
        val cityCoordDAO = CityCoordDAO()
        cityCoordDAO.save(city1)
        cityCoordDAO.save(city2)








    }


    override fun onResume() {
        super.onResume()
        runRequisition()
        settingUpRecyclerView()
        hideProgressBar()

    }

    private fun hideProgressBar() {
        val progressBar = findViewById<ProgressBar>(R.id.progressbar_main)

        progressBar.visibility = View.GONE

    }

    private fun settingUpRecyclerView() {
        val recyclerViewCity : RecyclerView = findViewById(R.id.recyclerview_cities)

        recyclerViewCity.layoutManager = LinearLayoutManager(this,LinearLayoutManager.HORIZONTAL, false)
        recyclerViewCity.adapter = CityWeatherAdapter(cityList)

    }

    private fun runRequisition() {
        val executor: ExecutorService = Executors.newSingleThreadExecutor()
        val tempoAtual = Weather(executor)
        tempoAtual.setCurrentWeather()
        while (CityDAO.cities.size == 0) {
            continue
        }

       cityList = CityDAO.cities

    }


}
