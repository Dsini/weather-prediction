package com.wheather.weather_prediction.model;

import java.util.Date;
import java.util.List;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class WeatherData {
	
	private String cod;
	private List<WeatherEntry> list;

}
