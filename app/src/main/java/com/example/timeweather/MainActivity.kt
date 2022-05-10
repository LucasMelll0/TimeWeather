package com.example.timeweather

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import androidx.lifecycle.LifecycleOwner
import androidx.lifecycle.lifecycleScope
import com.example.timeweather.requisition.Weather
import kotlinx.coroutines.launch
import kotlinx.coroutines.newSingleThreadContext
import java.lang.Exception
import java.util.concurrent.Executor
import java.util.concurrent.ExecutorService
import java.util.concurrent.Executors

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)


           try {
               val executor : ExecutorService = Executors.newSingleThreadExecutor()
               val tempoAtual = Weather(executor)
               tempoAtual.getWeather()
               Log.i("Teste", "rodou")
           }catch (e : Exception){
               Log.i("Teste", "Não rodou")
           }finally {

           }
       }



}
