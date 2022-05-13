package com.example.timeweather

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.AppCompatTextView
import com.example.timeweather.model.CurrentWeather
import com.example.timeweather.requisition.Weather
import java.util.concurrent.ExecutorService
import java.util.concurrent.Executors

class MainActivity : AppCompatActivity() {
    private lateinit var currentWeatherObject: CurrentWeather
    private lateinit var temperaturaAtual: AppCompatTextView
    private lateinit var horarioAtual: AppCompatTextView
    private lateinit var climaAtual: AppCompatTextView
    private lateinit var localizacao: AppCompatTextView
    private lateinit var maxTemp : AppCompatTextView
    private lateinit var minTemp : AppCompatTextView
    private lateinit var atualizadoEm : AppCompatTextView
    private lateinit var tempoAtual: Weather


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        setViewsById()
        val executor: ExecutorService = Executors.newSingleThreadExecutor()
        tempoAtual = Weather(executor)
        tempoAtual.setCurrentWeather()
        while (tempoAtual.objeto == null) {
            continue
        }


    }

    private fun setViewsById() {
        horarioAtual = findViewById(R.id.textview_ultima_atualizacao)
        temperaturaAtual = findViewById(R.id.textview_temperatura)
        climaAtual = findViewById(R.id.textview_clima)
        localizacao = findViewById(R.id.textview_local)
        maxTemp = findViewById(R.id.textview_temperatura_maxima)
        minTemp = findViewById(R.id.textview_temperatura_minima)
        atualizadoEm = findViewById(R.id.textview_ultima_atualizacao)
    }

    override fun onResume() {
        super.onResume()
        setWeather()

    }

    private fun setWeather() {
        currentWeatherObject = tempoAtual.objeto
        val climaAtualText = currentWeatherObject.weather.get(0).description.toString()
        val temperaturaAtualText = currentWeatherObject.main.temp.toInt().toString() + "Cº"
        val localText = currentWeatherObject.name.toString()
        val maxTempText = "Max " + currentWeatherObject.main.temp_max.toInt().toString() + "Cº"
        val minTempText = "Min " + currentWeatherObject.main.temp_min.toInt().toString() + "Cº"

        this.temperaturaAtual.text = temperaturaAtualText
        this.climaAtual.text = climaAtualText
        this.localizacao.text = localText
        this.maxTemp.text = maxTempText
        this.minTemp.text = minTempText
    }


}
