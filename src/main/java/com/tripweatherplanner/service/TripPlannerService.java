package com.tripweatherplanner.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.tripweatherplanner.model.PlaceRecommendation;
import com.tripweatherplanner.model.PlaceRecords;
import com.tripweatherplanner.model.WeatherRecords;

import lombok.AllArgsConstructor;

@Service
@AllArgsConstructor
public class TripPlannerService {
	
	private final PlacesService placeService;
	private final WeatherService weatherService;
	
	public List<PlaceRecommendation> getPlaceRecommendation(String location, String travelDate){
		List<PlaceRecords.Place> places = placeService.getTopTouristPlaces(location);
		
		return places.stream().map(place -> {
			List<String> photos = place.photos().stream().limit(2)
					.map(photo -> placeService.getPhotoURI(photo.name()))
					.toList();
			WeatherRecords.WeatherData weather = weatherService.getWeather(place.location(), travelDate);
			return new PlaceRecommendation(place, photos, weather);
		})
		.toList();
	}
}