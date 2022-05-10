package com.example.timeweather.requisition

import android.util.Log
import okhttp3.OkHttpClient
import okhttp3.Request
import okhttp3.Response
import okio.IOException
import java.lang.Exception
import java.util.concurrent.Executor

class Weather(
    private val executor: Executor
) {


    fun getWeather() {
        executor.execute {
            try {
                val client = OkHttpClient()

                val request = Request.Builder()
                    .url("https://community-open-weather-map.p.rapidapi.com/weather?q=London%2Cuk&lat=0&lon=0&callback=test&id=2172797&lang=null&units=imperial&mode=xml")
                    .get()
                    .addHeader("X-RapidAPI-Host", "community-open-weather-map.p.rapidapi.com")
                    .addHeader(
                        "X-RapidAPI-Key",
                        "a0c703d49dmsh22f075055d9c629p11c8d8jsnb7daa5c00934"
                    )
                    .build()

                val response = client.newCall(request).execute()

                for((name, value) in response.headers){
                    println("$name: $value")
                }
                Log.i("Testes", "getWeather: " + response)

            } catch (e: Exception) {
                e.printStackTrace()
            }


        }

    }

}

