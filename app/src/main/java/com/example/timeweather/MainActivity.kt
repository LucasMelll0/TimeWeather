package com.example.timeweather

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import androidx.appcompat.widget.AppCompatTextView
import com.example.timeweather.model.CurrentWeather
import com.example.timeweather.requisition.Weather
import java.lang.Exception
import java.util.concurrent.ExecutorService
import java.util.concurrent.Executors

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

            val horarioAtual = findViewById<AppCompatTextView>(R.id.textview_ultima_atualizacao)
           try {
               val executor : ExecutorService = Executors.newSingleThreadExecutor()
               val tempoAtual = Weather(executor)
               tempoAtual.getWeather()

               //horarioAtual.text = CurrentWeather.temperature
               Log.i("Teste", "rodou")
           }catch (e : Exception){
               Log.i("Teste", "Não rodou")
           }finally {

           }
       }



}
