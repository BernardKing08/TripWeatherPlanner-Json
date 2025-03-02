package com.tripweatherplanner.model;

import java.util.List;

public record PlaceRecommendation(PlaceRecords.Place place,
        List<String> photos, WeatherRecords.WeatherData weatherData) {
}