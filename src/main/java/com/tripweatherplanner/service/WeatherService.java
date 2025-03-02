package com.tripweatherplanner.service;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestClient;
import org.springframework.web.util.UriComponentsBuilder;

import com.tripweatherplanner.model.PlaceRecords;
import com.tripweatherplanner.model.WeatherRecords;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@Service
public class WeatherService {
	
	private RestClient weatherClient; 
	private String apiKey;
	
    public WeatherService(@Value("${weather.api.key}") String apiKey){
        this.apiKey = apiKey;

        weatherClient = RestClient.builder()
                .baseUrl("https://api.openweathermap.org/data/2.5")
                .build();
    }

    public WeatherRecords.WeatherData getWeather(PlaceRecords.Location location, String travelDate){
        String uriString = UriComponentsBuilder.fromUriString("/forecast?lat=-{lat}&lon={long}&appid={apiKey}&units=metric")
                .buildAndExpand(location.latitude(), location.longitude(), apiKey)
                .toUriString();
        var weatherResponse = weatherClient.get()
                .uri(uriString)
                .retrieve()
                .body(WeatherRecords.WeatherResponse.class);
        return weatherResponse.list().stream()
                .filter(weatherData -> weatherData.dtTxt().startsWith(travelDate))
                .findFirst()
                .orElseThrow(() -> new RuntimeException("No weather data found"));

    }

   
}

