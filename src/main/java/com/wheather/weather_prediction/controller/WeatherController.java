package com.wheather.weather_prediction.controller;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.wheather.weather_prediction.exception.ResourceNotFoundException;
import com.wheather.weather_prediction.model.WeatherData;
import com.wheather.weather_prediction.model.WeatherEntry;
import com.wheather.weather_prediction.services.WeatherService;
import com.wheather.weather_prediction.util.WeatherAnalyzer;


@RestController
@RequestMapping("/weather")
public class WeatherController {

 @Autowired
 private WeatherService weatherService;

 @Autowired
 private WeatherAnalyzer weatherAnalyzer;

 @GetMapping("/{city}")
 @CrossOrigin(origins = "http://localhost:4200")
 public ResponseEntity<List<WeatherEntry>> getWeatherInfo(@PathVariable String city) {
	 if (city == null || city.isBlank()) {
         return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
     }

    
    
     try {
    	 Optional<WeatherData> weatherData = weatherService.getWeatherForecast(city);
    	 List<WeatherEntry> result = new ArrayList<>();
    	 if(weatherData.isPresent()) {
          result = weatherAnalyzer.analyzeWeather(weatherData.get());
    	 }
         return new ResponseEntity<List<WeatherEntry>>(result, HttpStatus.OK);
     }
     catch (Exception ex) {
         throw new ResourceNotFoundException("weather not found for " + city +". Please enter correct city name");
     }
 }
}

