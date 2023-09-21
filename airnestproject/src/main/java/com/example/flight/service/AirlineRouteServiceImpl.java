package com.example.flight.service;

import java.io.File;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import com.example.flight.dto.GetDetailedItineraryResponse;
import com.example.flight.entity.Airline;
import com.example.flight.entity.AirlineRoute;
import com.example.flight.entity.Airport;
import com.example.flight.repository.AirlineRepository;
import com.example.flight.repository.AirlineRouteRepo;
import com.example.flight.repository.AirportRepository;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;

@Service


public class AirlineRouteServiceImpl implements AirlineRouteService {


	@Autowired
	AirlineRouteRepo airlineRouteRepo;
	@Autowired
	RestTemplate airportConfig;
	
	@Autowired
	private AirlineRepository ar;
	
	@Autowired
	private AirportRepository airportRepo;
	public List<AirlineRoute> getAllAirlineRoutes() {
		List<AirlineRoute> dataList = new ArrayList();

		ObjectMapper objectMapper = new ObjectMapper();

	try {

			// Specify the path to your JSON file

			File jsonFile = new File("src/main/resources/json/flightsDB.routes_v2.json");

			// Read JSON data into a List of MyJsonObject

			dataList = objectMapper.readValue(jsonFile, new TypeReference<List<AirlineRoute>>() {

			});

 

		// Now you can work with the 'dataList' which contains a list of JSON objects.

			for (AirlineRoute obj : dataList) {

				airlineRouteRepo.save(obj);

			}

		} catch (IOException e) {

			e.printStackTrace();

		}

		return dataList;

 

 

 

}

 

 

 

	public List<AirlineRoute> getAllRoutes() {

		return airlineRouteRepo.findAll();

	}

 

 

 

	

//	public List<AirlineRoute> getBestRecommendations(AirlineRoute request) {

//		// TODO Auto-generated method stub

//		return null;

//	}

 

 

 

	

 

 

//	public List<AirlineRoute> getBestRecommendations(AirlineRoute request) {

//		String iataFrom =request.getIataFrom();

//		String iataTo=request.getIataTo();

//		boolean classBusiness=request.isClassBusiness();

//		boolean classEconomy=request.isClassEconomy();

//		boolean classFirst=request.isClassFirst();

//		List<AirlineRoute>recommendations=airlineRouteRepo.findBestRoutes(iataFrom, iataTo, classBusiness, classEconomy, classFirst,);

//		return recommendations;

//	}

 

@Override

		public List<GetDetailedItineraryResponse> getDetailedItinerary(List<Integer> routeId) {

			List<GetDetailedItineraryResponse> itineraryList = new ArrayList<>();

			for (int id : routeId) {

 

	

 

				AirlineRoute getRoute = airlineRouteRepo.findById(id).get();

				String airlineIata = getRoute.getAirLineIata();

				String airportToIata = getRoute.getIataTo();

				String airportFromIata = getRoute.getIataFrom();

				Airline getAirline = airportConfig

						.getForObject("http://localhost:8200/api/routes/getAirline/" + airlineIata, Airline.class);

				Airport getAirportTo = airportConfig

						.getForObject("http://localhost:8200/api/routes/getAirport/" + airportToIata, Airport.class);

				Airport getAirportFrom = airportConfig

						.getForObject("http://localhost:8200/api/routes/getAirport/" + airportFromIata, Airport.class);

				GetDetailedItineraryResponse itineraryResponse = new GetDetailedItineraryResponse(id, getAirportFrom,

						getAirportTo, getAirline);

				itineraryList.add(itineraryResponse);

			}

 

	

 

			return itineraryList;

		}

 

//AirlineService

//@Autowired
//public AirlineService(AirlineRepository jsonDataRepository) {
//    this.jsonDataRepository = jsonDataRepository;
//}



public List<Airline> getAllAirlines() {
    return ar.findAll();
}

public String readJsonFromFileSystem(String filePath) throws IOException {
    byte[] bytes = Files.readAllBytes(Paths.get(filePath));
    String jsonContent= new String(bytes, StandardCharsets.UTF_8);
    ObjectMapper objectMapper = new ObjectMapper(); // Assuming you have Jackson ObjectMapper
    Airline[] airline=objectMapper.readValue(jsonContent,Airline[].class);
    List<Airline> airlines=new ArrayList();
    for(Airline am:airline) {
    	airlines.add(am);
    }

    ar.saveAll(airlines);

    return  new String(bytes, StandardCharsets.UTF_8);
//    ObjectMapper objectMapper = new ObjectMapper();
}
public Airline getAirlineByCode(String code) {
	return ar.findByCode(code);
}



//Airport


public List<Airport> getAllJsonData() {
    return airportRepo.findAll();
}

public String readJsonFromFileSystems(String filePath) throws IOException {
    byte[] bytes = Files.readAllBytes(Paths.get(filePath));
    String jsonContent= new String(bytes, StandardCharsets.UTF_8);
    ObjectMapper objectMapper = new ObjectMapper(); // Assuming you have Jackson ObjectMapper
    Airport[] airline=objectMapper.readValue(jsonContent,Airport[].class);
    List<Airport> airlines=new ArrayList();
    for(Airport am:airline) {
    	airlines.add(am);
    }
for(Airport am:airline) {
	try {
    airportRepo.save(am);
	}
	catch (Exception e) {
		// TODO: handle exception
		
		System.out.println(am.getState());
		
	}
	
}

    return  new String(bytes, StandardCharsets.UTF_8);
//    ObjectMapper objectMapper = new ObjectMapper();
}
public Airport findByCode(String code){
	return airportRepo.findByCode(code);
}
}

