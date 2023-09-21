package com.example.flight.service;

import java.util.List;

import com.example.flight.dto.GetDetailedItineraryResponse;
import com.example.flight.entity.AirlineRoute;


public interface AirlineRouteService {

	public List<AirlineRoute> getAllAirlineRoutes();

	public List<AirlineRoute> getAllRoutes();

//	List<AirlineRoute>getBestRecommendations(AirlineRoute request);

	public List<GetDetailedItineraryResponse> getDetailedItinerary(List<Integer> routeIds);

	}