package com.wheather.weather_prediction.services;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import com.wheather.weather_prediction.model.WeatherData;

import io.github.resilience4j.circuitbreaker.annotation.CircuitBreaker;
import io.github.resilience4j.retry.annotation.Retry;

//WeatherService.java
@Service
public class WeatherService {

 @Value("${weather.api.key}")
 private String apiKey;
 @Value("${weather.api.url}")
 private String apiurl;
 @Value("${weather.api.count}")
 private int apiCount;

 
 @Retry(name = "default", fallbackMethod = "fallbackMethod")
 @CircuitBreaker(name = "default", fallbackMethod = "fallbackMethod")
 public Optional<WeatherData> getWeatherForecast(String city) {
     String url = apiurl + city +
                  "&appid=" + apiKey + "&cnt="+apiCount;
     RestTemplate restTemplate = new RestTemplate();
     WeatherData w = restTemplate.getForObject(url, WeatherData.class);
     return Optional.of(w);
 }
 
 public Optional<WeatherData> fallbackMethod(String id,Exception e) {
     // Fallback logic when the public API is not available
     return Optional.of(new WeatherData());
 }
}

