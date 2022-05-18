package com.example.timeweather

import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.ProgressBar
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.lifecycleScope
import androidx.lifecycle.whenCreated
import androidx.lifecycle.whenResumed
import androidx.lifecycle.whenStarted
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.timeweather.DAO.CityDAO
import com.example.timeweather.model.City
import com.example.timeweather.requisition.Weather
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import java.util.concurrent.ExecutorService
import java.util.concurrent.Executors

class MainActivity : AppCompatActivity() {

    private lateinit var citiesList : ArrayList<City>
    private lateinit var progressBar: ProgressBar
    private lateinit var adapter: CityWeatherAdapter
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        progressBar = findViewById(R.id.progressbar_main)
        settingUpRecyclerView()
        runRequisition()


    }


    override fun onResume() {
        super.onResume()



    }


    private fun settingUpRecyclerView() {
        adapter = CityWeatherAdapter(CityDAO.cities)
        val recyclerViewCity: RecyclerView = findViewById(R.id.recyclerview_cities)

        recyclerViewCity.layoutManager =
            LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL, false)
        recyclerViewCity.adapter = adapter

    }

    private fun runRequisition() {
        lifecycleScope.launch {
            whenStarted {
                progressBar.visibility = View.VISIBLE

                val loadingCities = withContext(Dispatchers.IO){

                    val tempoAtual = Weather()
                    tempoAtual.getCurrentWeather()
                }
            }
            adapter.notifyDataSetChanged()
             progressBar.visibility = View.GONE




        }


    }


}
