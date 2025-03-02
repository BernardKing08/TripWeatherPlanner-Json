package com.tripweatherplanner.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.tripweatherplanner.model.PlaceRecommendation;
import com.tripweatherplanner.service.TripPlannerService;

import lombok.AllArgsConstructor;


@RestController
@AllArgsConstructor
@RequestMapping("/trip-planner")
public class TripPlannerController {

    private final TripPlannerService tripPlannerService;

    @GetMapping("/{location}/{travelDate}")
    public List<PlaceRecommendation> getPlaceRecommendation(@PathVariable String location,
                                                            @PathVariable String travelDate){
        return tripPlannerService.getPlaceRecommendation(location,travelDate);
    }

}