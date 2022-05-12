package com.example.timeweather.model;

import com.fasterxml.jackson.annotation.JsonGetter;
import com.fasterxml.jackson.annotation.JsonSetter;

public class CurrentWeather {
    protected String date;
    protected String temperature;

    public CurrentWeather(String date, String temperature) {
        this.date = date;
        this.temperature = temperature;
    }


}
