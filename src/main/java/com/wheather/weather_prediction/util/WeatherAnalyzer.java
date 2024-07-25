package com.wheather.weather_prediction.util;

import java.text.DecimalFormat;
import java.time.LocalDate;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.stereotype.Component;

import com.wheather.weather_prediction.model.Main;
import com.wheather.weather_prediction.model.WeatherData;
import com.wheather.weather_prediction.model.WeatherEntry;

//WeatherAnalyzer.java
@Component
public class WeatherAnalyzer {
	
	private static List<WeatherEntry> filterNextThreeDays(WeatherData weatherData) {
     
        
        List<WeatherEntry> weatherDataList = weatherData.getList();
       // List<WeatherEntry>  mapfilteredDataList =  weatherDataList.stream().map(e -> e.setFdt_txt(LocalDate.parse(e.getDt_txt().subSequence(0, 10)))).collect(Collectors.toList());
        
        List<WeatherEntry>  filteredDataList =  weatherDataList.stream()
        		
        		.filter(d -> LocalDate.parse(d.getDt_txt().subSequence(0, 10)).isAfter(LocalDate.now()) && LocalDate.parse(d.getDt_txt().subSequence(0, 10)).isBefore(LocalDate.now().plusDays(4)))
        		
        		.collect(Collectors.toList());
        
        
       

        return filteredDataList;
    }

 public List<WeatherEntry> analyzeWeather(WeatherData weatherData) {
    
     
     
     List<WeatherEntry> entries = filterNextThreeDays(weatherData);

     for (WeatherEntry entry : entries) 
     {
    	 StringBuilder result = new StringBuilder();
    	 Main main = entry.getMain();
    	 double temperature = main.getTemp_max() - 273.15; 
    	 double maxtemperature =main.getTemp_max() - 273.15;  
    	 double mintemperature =main.getTemp_min() - 273.15;
    	 DecimalFormat df = new DecimalFormat("#.##");
    	 main.setTemp(Double.valueOf(df.format(temperature)));
    	 main.setTemp_min(Double.valueOf(df.format(mintemperature)));
    	 main.setTemp_max(Double.valueOf(df.format(maxtemperature)));
    	 
    	  if (main.getTemp_max()>40) {
    		  result.append("Use sunscreen lotion ");
    	  }
    	  if (entry.getWeather().get(0).getMain().equalsIgnoreCase("Rain")) {
    		  result.append(" Carry umbrella  ");
    	  }
    	  if (entry.getWind().getSpeed() > 10) {
    		  result.append(" It's too windy, watch out   ");
    	  }
    	  if (entry.getWeather().get(0).getMain().equalsIgnoreCase("Thunderstorms")) {
    		  result.append("Don’t step out! A Storm is brewing!");
    	  }
 		 
    	entry.setWarnings(result.toString());
     }
		/*
		 * for (WeatherEntry entry : entries) {
		 * 
		 * Main main = entry.getMain(); List<Weather> weather = entry.getWeather(); Wind
		 * wind = entry.getWind(); result.append("Date - "+entry.getDt_txt() + "\n");
		 * double temperature = main.getTemp_max() - 273.15; double maxtemperature =
		 * main.getTemp_max() - 273.15; // Convert to Celsius double mintemperature =
		 * main.getTemp_min() - 273.15;
		 * 
		 * result.append("Max temperature"+ String.format("%.2f", maxtemperature)+"\n");
		 * result.append("Min temperature"+ String.format("%.2f", mintemperature)+"\n");
		 * // Check conditions if (weather.stream().anyMatch(w ->
		 * w.getMain().equals("Rain"))) { result.append("Carry umbrella " +"\n"); } if
		 * (temperature > 40) { result.append("Use sunscreen lotion "+"\n"); } if
		 * (wind.getSpeed() > 10) { result.append("It's too windy, watch out "+"\n"); }
		 * if (weather.stream().anyMatch(w -> w.getMain().equals("Thunderstorm"))) {
		 * result.append("Don't step out! A Storm is brewing "+"\n"); }
		 * 
		 * 
		 * }
		 */

     return entries;
 }
}

