package com.example.timeweather.requisition;

import android.util.Log;

import com.example.timeweather.model.CityCoordDAO;
import com.example.timeweather.model.CityDAO;
import com.example.timeweather.model.City;
import com.example.timeweather.model.CityCoord;
import com.example.timeweather.model.CurrentWeather;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.io.IOException;
import java.util.ArrayList;
import java.util.concurrent.Executor;

import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

public class Weather {
    private Executor executor;



    public Weather(Executor executor){
        this.executor = executor;
    }

    public void setCurrentWeather() {

        ArrayList<City> preCityList = new ArrayList<>();


        executor.execute(new Runnable() {
            @Override
            public void run() {
                try {
                    CityCoordDAO ccDAO = new CityCoordDAO();

                    Log.i("Testes", "getWeather: começou a rodar");

                    for (int i = 0; i < CityCoordDAO.getCitiesCoords().size(); i++) {
                        OkHttpClient cliente = new OkHttpClient();
                        CityCoord cityCoord = CityCoordDAO.getCitiesCoords().get(i);
                        String lat = cityCoord.getLat();
                        String lon = cityCoord.getLon();
                        Request request = new Request.Builder()
                                .url("https://community-open-weather-map.p.rapidapi.com/weather?lat=" + lat + "&lon=" + lon +"&callback=&id=2172797&lang=pt&units=metric&mode=json")
                                .addHeader("X-RapidAPI-Host", "community-open-weather-map.p.rapidapi.com")
                                .addHeader("X-RapidAPI-Key", "a0c703d49dmsh22f075055d9c629p11c8d8jsnb7daa5c00934")
                                .build();

                        Response response = cliente.newCall(request).execute();
                        ObjectMapper objectMapper = new ObjectMapper();


                        CurrentWeather objeto = objectMapper.readValue(response.body().string(), CurrentWeather.class);
                        Log.i("Testes", "Cidade: " + objeto.getMain() + "lat: " + objeto.getCoord().getLat() + "lon: " + objeto.getCoord().getLon());
                        String nome = objeto.getName();
                        String clima = objeto.getWeather().get(0).getDescription();
                        float temperatura = objeto.getMain().getTemp();
                        float min_temp = objeto.getMain().getTemp_min();
                        float max_temp = objeto.getMain().getTemp_max();

                        City city = new City(nome, clima, temperatura, min_temp, max_temp);
                        Log.i("Testes", "cidade: " + city.getName());
                        preCityList.add(city);

                    }
                    CityDAO cityDAO = new CityDAO();
                    cityDAO.saveALl(preCityList);




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

}
