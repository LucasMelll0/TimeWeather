package com.example.timeweather.requisition;

import android.util.Log;

import com.example.timeweather.model.CurrentWeather;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.google.gson.Gson;

import java.util.concurrent.Executor;

import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;
import okhttp3.ResponseBody;

public class Weather {
    private Executor executor;
    public Weather(Executor executor){
        this.executor = executor;
    }

    public void getWeather(){
        executor.execute(new Runnable() {
            @Override
            public void run() {
                try {
                    Log.i("Testes", "getWeather: começou a rodar");
                    OkHttpClient cliente = new OkHttpClient();

                    Request request = new Request.Builder()
                            .url("https://community-open-weather-map.p.rapidapi.com/weather?q=London%2Cuk&lat=0&lon=0&callback=true&id=2172797&lang=null&units=metric&mode=json")
                            .addHeader("X-RapidAPI-Host", "community-open-weather-map.p.rapidapi.com")
                            .addHeader("X-RapidAPI-Key", "a0c703d49dmsh22f075055d9c629p11c8d8jsnb7daa5c00934")
                            .build();

                    Response response = cliente.newCall(request).execute();

                    CurrentWeather sampleResponse = new CurrentWeather("01/01/2001", "0º");

                    ObjectMapper objectMapper = new ObjectMapper();
                    Gson gson = new Gson();
                    ResponseBody responseBody = response.body();
                    CurrentWeather entity = gson.fromJson(responseBody.string(), CurrentWeather.class);

                    //Log.i("Testes", "run: " + entity.getTemperature());


                    Log.i("Testes", "getWeather: " + response);
                    Log.i("Testes", "getWeather: " + response.body().string());

                }catch (Exception e){
                    e.printStackTrace();
                }
            }
        });

    }
}
