package com.example.timeweather

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.ProgressBar
import androidx.appcompat.widget.AppCompatTextView
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.lifecycle.lifecycleScope
import com.example.timeweather.model.City
import com.example.timeweather.model.WeatherCityForecast
import com.example.timeweather.requisition.Weather
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import java.text.DecimalFormat

class ForecastWeatherActivity : AppCompatActivity() {

    private lateinit var progressBar: ProgressBar
    private lateinit var constraintLayout: ConstraintLayout
    private lateinit var city_name: AppCompatTextView
    private lateinit var now_temp: AppCompatTextView
    private lateinit var now_min_temp: AppCompatTextView
    private lateinit var now_max_temp: AppCompatTextView
    private lateinit var one_day_temp: AppCompatTextView
    private lateinit var one_day_min_temp: AppCompatTextView
    private lateinit var one_day_max_temp: AppCompatTextView
    private lateinit var two_day_temp: AppCompatTextView
    private lateinit var two_day_min_temp: AppCompatTextView
    private lateinit var two_day_max_temp: AppCompatTextView
    private lateinit var three_day_temp: AppCompatTextView
    private lateinit var three_day_min_temp: AppCompatTextView
    private lateinit var three_day_max_temp: AppCompatTextView
    private lateinit var four_day_temp: AppCompatTextView
    private lateinit var four_day_min_temp: AppCompatTextView
    private lateinit var four_day_max_temp: AppCompatTextView
    private lateinit var five_day_temp: AppCompatTextView
    private lateinit var five_day_min_temp: AppCompatTextView
    private lateinit var five_day_max_temp: AppCompatTextView


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_forecast_weather)
        initViews()
        runRequisition()

    }

    private fun initViews() {
        progressBar = findViewById(R.id.progressbar_forecast)
        constraintLayout = findViewById(R.id.constraint_layout_forecast)
        city_name = findViewById(R.id.textview_city_name_forecast)
        now_temp = findViewById(R.id.textview_weather_now_temp)
        now_min_temp = findViewById(R.id.textview_weather_now_min_temp)
        now_max_temp = findViewById(R.id.textview_weather_now_max_temp)
        one_day_temp = findViewById(R.id.textview_weather_one_day_temp)
        one_day_min_temp = findViewById(R.id.textview_weather_one_day_min_temp)
        one_day_max_temp = findViewById(R.id.textview_weather_one_day_max_temp)
        two_day_temp = findViewById(R.id.textview_weather_two_day_temp)
        two_day_min_temp = findViewById(R.id.textview_weather_two_day_min_temp)
        two_day_max_temp = findViewById(R.id.textview_weather_two_day_max_temp)
        three_day_temp = findViewById(R.id.textview_weather_three_day_temp)
        three_day_min_temp = findViewById(R.id.textview_weather_three_day_min_temp)
        three_day_max_temp = findViewById(R.id.textview_weather_three_day_max_temp)
        four_day_temp = findViewById(R.id.textview_weather_four_day_temp)
        four_day_min_temp = findViewById(R.id.textview_weather_four_day_min_temp)
        four_day_max_temp = findViewById(R.id.textview_weather_four_day_max_temp)
        five_day_temp = findViewById(R.id.textview_weather_five_day_temp)
        five_day_min_temp = findViewById(R.id.textview_weather_five_day_min_temp)
        five_day_max_temp = findViewById(R.id.textview_weather_five_day_max_temp)
    }

    private fun runRequisition() {
        lifecycleScope.launch {
            progressBar.visibility = View.VISIBLE
            val forecastData : WeatherCityForecast

            val loadingForecast = withContext(Dispatchers.IO){

                val data = intent
                val city : City = data.getSerializableExtra("city") as City
                val forecast = Weather()
                forecastData = forecast.getForecastFor5Days(city)




            }
            loadingDataIntoViews(forecastData)
            progressBar.visibility = View.GONE
            constraintLayout.visibility = View.VISIBLE


        }
    }

    private fun loadingDataIntoViews(forecastData: WeatherCityForecast) {
        val decimalFormat = DecimalFormat("##.#")
        city_name.text = forecastData.name
        now_temp.text = "${decimalFormat.format(forecastData.firstDay.temp)}Cº"
        now_min_temp.text = "Min${decimalFormat.format(forecastData.firstDay.temp_min)}Cº"
        now_max_temp.text = "Max${decimalFormat.format(forecastData.firstDay.temp_max)}Cº"
        one_day_temp.text = "${decimalFormat.format(forecastData.secondDay.temp)}Cº"
        one_day_min_temp.text = "Min${decimalFormat.format(forecastData.secondDay.temp_min)}Cº"
        one_day_max_temp.text = "Max${decimalFormat.format(forecastData.secondDay.temp_max)}Cº"
        two_day_temp.text = "${decimalFormat.format(forecastData.thirtDay.temp)}Cº"
        two_day_min_temp.text = "Min${decimalFormat.format(forecastData.thirtDay.temp_min)}Cº"
        two_day_max_temp.text = "Max${decimalFormat.format(forecastData.thirtDay.temp_max)}Cº"
        three_day_temp.text = "${decimalFormat.format(forecastData.forthDay.temp)}Cº"
        three_day_min_temp.text = "Min${decimalFormat.format(forecastData.forthDay.temp_min)}Cº"
        three_day_max_temp.text = "Max${decimalFormat.format(forecastData.forthDay.temp_max)}Cº"
        four_day_temp.text = "${decimalFormat.format(forecastData.fifthDay.temp)}Cº"
        four_day_min_temp.text = "Min${decimalFormat.format(forecastData.fifthDay.temp_min)}Cº"
        four_day_max_temp.text = "Max${decimalFormat.format(forecastData.fifthDay.temp_max)}Cº"
        five_day_temp.text = "${decimalFormat.format(forecastData.sixthDay.temp)}Cº"
        five_day_min_temp.text = "Min${decimalFormat.format(forecastData.sixthDay.temp_min)}Cº"
        five_day_max_temp.text = "Max${decimalFormat.format(forecastData.sixthDay.temp_max)}Cº"



    }
}