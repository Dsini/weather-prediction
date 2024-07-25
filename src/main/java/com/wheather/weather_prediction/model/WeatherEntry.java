package com.wheather.weather_prediction.model;

import java.time.LocalDate;
import java.util.List;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class WeatherEntry {
	
	private Main main;
    private List<Weather> weather;
    private Wind wind;
    private String dt_txt;
    private String warnings;
   
    

}
