package com.example.flight.controller;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.flight.dto.GetDetailedItineraryResponse;
import com.example.flight.entity.Airline;
import com.example.flight.entity.AirlineRoute;
import com.example.flight.entity.Airport;
import com.example.flight.entity.AirportResult;
import com.example.flight.repository.AirlineRouteRepo;
import com.example.flight.repository.AirportRepository;
import com.example.flight.service.AirlineRouteService;
import com.example.flight.service.AirlineRouteServiceImpl;



@RestController
@RequestMapping("/api/routes")

 

public class AirlineRouteController {

 

	@Autowired
	AirlineRouteService airlineRouteService;

	@Autowired
	AirlineRouteRepo ar;
	
	@Autowired
	private AirlineRouteServiceImpl jds;
	
	@Autowired
	AirportRepository airportRepo;


	@GetMapping("fetch-save")
	public List<AirlineRoute> fetchAllAirlineRoute() {
		return airlineRouteService.getAllAirlineRoutes();
	}



	@GetMapping("/getAll")
	public ResponseEntity<List<AirlineRoute>> getAllAirlines() {
		List<AirlineRoute>airlines=airlineRouteService.getAllRoutes();
		return ResponseEntity.ok(airlines);
	}

 

	@PostMapping("/best")

 

	public List<AirlineRoute> getRoute(@RequestBody AirlineRoute model){
		List<AirlineRoute> data = new ArrayList<>();
	List<Boolean> category = Arrays.asList(false, false, false);
		if (model.getClassType().equalsIgnoreCase("Business")) {
			category.set(0, true);
		} else if (model.getClassType().equalsIgnoreCase("Economy")) {
			category.set(1, true);
		} else if (model.getClassType().equalsIgnoreCase("first")) {
			category.set(2, true);
		}
//		else return null;
		List<String> dayStatus = Arrays.asList("no", "no", "no", "no", "no", "no", "no");
		int day=model.getDate().getDayOfWeek().getValue();

		if(day==0) {
			dayStatus.set(7,"yes");
		}else {
		dayStatus.set(day, "yes");
		}
//		
//		for(AirlineRoute r: ar.findAll()) {
//			if ((r.getIataFrom().equalsIgnoreCase(model.getIataFrom())) && 
//				    (r.getIataTo().equalsIgnoreCase(model.getIataTo())) &&
//				    (
//				        (r.getDay1().equals(dayStatus.get(0)) && dayStatus.get(0).equals("yes")) ||
//				        (r.getDay2().equals(dayStatus.get(1)) && dayStatus.get(1).equals("yes")) ||
//				        (r.getDay3().equals(dayStatus.get(2)) && dayStatus.get(2).equals("yes")) ||
//				        (r.getDay4().equals(dayStatus.get(3)) && dayStatus.get(3).equals("yes")) ||
//				        (r.getDay5().equals(dayStatus.get(4)) && dayStatus.get(4).equals("yes")) ||
//				        (r.getDay6().equals(dayStatus.get(5)) && dayStatus.get(5).equals("yes")) ||
//				        (r.getDay7().equals(dayStatus.get(6)) && dayStatus.get(6).equals("yes"))
//				    ) &&
//				    (
//				        (r.isClassBusiness() == category.get(0)) &&
//				        (r.isClassEconomy() == category.get(1)) &&
//				        (r.isClassFirst() == category.get(2))
//				    )
//				) {
//				    data.add(r);
//				}
//
//			}
//		

 

		return ar.findBestRoutes(model.getIataFrom(), model.getIataTo(),category.get(0) , category.get(1), category.get(2), dayStatus.get(0), dayStatus.get(1), dayStatus.get(2), dayStatus.get(3), dayStatus.get(4), dayStatus.get(5), dayStatus.get(6));
}
	@PostMapping("/multicity-best")
	public ResponseEntity<List<AirlineRoute>> getMultiCityRoutes(@RequestBody List<AirlineRoute> routes) {
	    List<AirlineRoute> data = new ArrayList<>();
	    // Iterate through the list of routes and find the best routes for each
	    for (AirlineRoute route : routes) {
	        List<AirlineRoute> bestRoutes = findBestRoutesForRoute(route);
	        data.addAll(bestRoutes);
	    }
	    return ResponseEntity.ok(data);
	}



	private List<AirlineRoute> findBestRoutesForRoute(AirlineRoute route) {
	    List<Boolean> category = Arrays.asList(false, false, false);
	    if (route.getClassType().equalsIgnoreCase("Business")) {
	        category.set(0, true);
	    } else if (route.getClassType().equalsIgnoreCase("Economy")) {
	        category.set(1, true);
	    } else if (route.getClassType().equalsIgnoreCase("first")) {
	        category.set(2, true);
	    }
	    List<String> dayStatus = Arrays.asList("no", "no", "no", "no", "no", "no", "no");
	    int day = route.getDate().getDayOfWeek().getValue();
	    if (day == 0) {
	        dayStatus.set(6, "yes");
	    } else {
	        dayStatus.set(day - 1, "yes");
	    }
   return ar.findBestRoutes(route.getIataFrom(), route.getIataTo(), category.get(0), category.get(1), category.get(2), dayStatus.get(0), dayStatus.get(1), dayStatus.get(2), dayStatus.get(3), dayStatus.get(4), dayStatus.get(5), dayStatus.get(6));
	}
	@PostMapping("/getdetaileditinerary")

	public List<GetDetailedItineraryResponse> fetchDetailedItinerary(@RequestBody List<Integer> routeIds){

		return airlineRouteService.getDetailedItinerary(routeIds);

	}
	
	
	//AirlineCopntroller
	

    @GetMapping("/save-airline")
    public ResponseEntity<String> fetchJSONData() throws IOException {
      

    	return ResponseEntity.ok(jds.readJsonFromFileSystem("src/main/resources/json/airlinesdb.json"));
    }
    @GetMapping("/getAllAirline")
    public ResponseEntity<List<Airline>> getAllAirlines1() {
        List<Airline> airlines = jds.getAllAirlines();
        return  ResponseEntity.ok(airlines);
    }
    @GetMapping("/getAirline/{code}")
public ResponseEntity<Airline> getAirlineByCode(@PathVariable String code){
	Airline entity=jds.getAirlineByCode(code);
	if(entity==null)
		return ResponseEntity.notFound().build();
		
	else
		return ResponseEntity.ok(entity);
	
}
    
    
    //AirportController
    
    @GetMapping("/save-airport")
    public ResponseEntity<String> fetchJSONData1() throws IOException {
    	return ResponseEntity.ok(jds.readJsonFromFileSystem("src/main/resources/json/airport.json"));
    }
    @GetMapping("/getAllAirport")
    public ResponseEntity<List<Airport>> getAllAirports() {
        List<Airport> airport = jds.getAllJsonData();
        return  ResponseEntity.ok(airport);
    }
    @GetMapping("/getAirport/{code}")
    public ResponseEntity<Airport>findByCode(@PathVariable String code){
    	Airport airport=jds.findByCode(code);
    	if(airport==null)
    		return ResponseEntity.noContent().build();
    	else
    		
    	return ResponseEntity.ok(airport);
    		
    }
    @PostMapping("/autocomplete")

    public ResponseEntity<Optional<List<AirportResult>>> autocompleteAirports(@RequestBody Map<String, String> requestBody) {

        String searchString = requestBody.get("search_string");

 

        // Call the repository method to get airport suggestions

        Optional<List<AirportResult>> airportSuggestions = airportRepo.getAirportSuggestionsWithDetails(searchString);

 

        return ResponseEntity.ok(airportSuggestions);

    }
}

 


 

