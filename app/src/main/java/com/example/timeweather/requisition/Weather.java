package com.example.timeweather.requisition;

import android.util.Log;


import com.example.timeweather.DAO.CityCoordDAO;
import com.example.timeweather.DAO.CityDAO;
import com.example.timeweather.model.City;
import com.example.timeweather.model.CityCoord;
import com.example.timeweather.model.CurrentWeather;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
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

                        Calendar nowObject = Calendar.getInstance();

                        String agora = getNow(nowObject);


                        City city = new City(nome, agora, clima, temperatura, min_temp, max_temp);
                        Log.i("Testes", "cidade: " + city.getNome());
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

    private String getNow(Calendar nowObject) {
        long agora = nowObject.getTimeInMillis();
        SimpleDateFormat formater = new SimpleDateFormat("HH:mm");

        return formater.format(agora);
    }

}
