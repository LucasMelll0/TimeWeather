package com.example.timeweather.model.jsonClasses;

import com.example.timeweather.model.jsonClasses.Clouds;
import com.example.timeweather.model.jsonClasses.DescriptionClass;
import com.example.timeweather.model.jsonClasses.Main;
import com.example.timeweather.model.jsonClasses.Sys;
import com.example.timeweather.model.jsonClasses.Wind;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import java.util.ArrayList;


@JsonIgnoreProperties(ignoreUnknown = true)
public class WeatherForecastForJson {
    private int dt;
    private Main main;
    private ArrayList<DescriptionClass> weather;
    private Clouds clouds;
    private Wind wind;
    private int visibility;
    private Double pop;
    private Sys sys;
    private String dt_txt;


    public int getDt() {
        return dt;
    }

    public void setDt(int dt) {
        this.dt = dt;
    }

    public Main getMain() {
        return main;
    }

    public void setMain(Main main) {
        this.main = main;
    }

    public ArrayList<DescriptionClass> getWeather() {
        return weather;
    }

    public void setWeather(ArrayList<DescriptionClass> weather) {
        this.weather = weather;
    }

    public Clouds getClouds() {
        return clouds;
    }

    public void setClouds(Clouds clouds) {
        this.clouds = clouds;
    }

    public Wind getWind() {
        return wind;
    }

    public void setWind(Wind wind) {
        this.wind = wind;
    }

    public int getVisibility() {
        return visibility;
    }

    public void setVisibility(int visibility) {
        this.visibility = visibility;
    }

    public Double getPop() {
        return pop;
    }

    public void setPop(Double pop) {
        this.pop = pop;
    }

    public Sys getSys() {
        return sys;
    }

    public void setSys(Sys sys) {
        this.sys = sys;
    }

    public String getDt_txt() {
        return dt_txt;
    }

    public void setDt_txt(String dt_txt) {
        this.dt_txt = dt_txt;
    }
}
