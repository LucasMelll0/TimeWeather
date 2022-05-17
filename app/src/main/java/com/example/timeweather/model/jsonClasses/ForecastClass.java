package com.example.timeweather.model.jsonClasses;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import java.util.ArrayList;

@JsonIgnoreProperties(ignoreUnknown = true)
public class ForecastClass {
    public ArrayList<WeatherForecastForJson> getList() {
        return list;
    }

    public void setList(ArrayList<WeatherForecastForJson> list) {
        this.list = list;
    }

    private ArrayList<WeatherForecastForJson> list;

}
