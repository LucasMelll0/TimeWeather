package com.example.timeweather.requisition;

import android.util.Log;

import com.example.timeweather.model.CurrentWeather;
import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.io.JsonEOFException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.google.gson.JsonArray;
import com.google.gson.JsonIOException;
import com.google.gson.JsonObject;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.Executor;

import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;
import okhttp3.ResponseBody;

public class Weather {
    private Executor executor;


    private CurrentWeather objeto;
    public Weather(Executor executor){
        this.executor = executor;
    }

    public void setCurrentWeather() {


        executor.execute(new Runnable() {
            @Override
            public void run() {
                try {
                    Log.i("Testes", "getWeather: começou a rodar");
                    OkHttpClient cliente = new OkHttpClient();

                    Request request = new Request.Builder()
                            .url("https://community-open-weather-map.p.rapidapi.com/weather?q=Natividade%2Cbr&lat=-21.0422&lon=-41.9733&callback=&id=2172797&lang=pt&units=metric&mode=json")
                            .addHeader("X-RapidAPI-Host", "community-open-weather-map.p.rapidapi.com")
                            .addHeader("X-RapidAPI-Key", "a0c703d49dmsh22f075055d9c629p11c8d8jsnb7daa5c00934")
                            .build();

                    Response response = cliente.newCall(request).execute();
                    ObjectMapper objectMapper = new ObjectMapper();


                    objeto = objectMapper.readValue(response.body().string(), CurrentWeather.class);
                    Log.i("Testes", "temperatura recebida: " + objeto.getMain().getTemp() + "Maxima de: " + objeto.getMain().getTemp_max() + "minima de: " + objeto.getMain().getTemp_min());


                }catch (JsonMappingException e){
                    e.printStackTrace();
                } catch (JsonProcessingException e) {
                    e.printStackTrace();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        });

    }

    public CurrentWeather getObjeto() {
        return objeto;
    }
}
