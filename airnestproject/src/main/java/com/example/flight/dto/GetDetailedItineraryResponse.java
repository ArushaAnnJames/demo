package com.example.flight.dto;

import com.example.flight.entity.Airline;
import com.example.flight.entity.Airport;

public class GetDetailedItineraryResponse {

	public int id;

	public Airport flyingFrom;

	public Airport flyingTo;

	public Airline airline;

	

	

	public GetDetailedItineraryResponse(int id, Airport flyingFrom, Airport flyingTo, Airline airline) {

		super();

		this.id = id;

		this.flyingFrom = flyingFrom;

		this.flyingTo = flyingTo;

		this.airline = airline;

	}

	public int getId() {

		return id;

	}

	public void setId(int id) {

		this.id = id;

	}

	public Airport getFlyingFrom() {

		return flyingFrom;

	}

	public void setFlyingFrom(Airport flyingFrom) {

		this.flyingFrom = flyingFrom;

	}

	public Airport getFlyingTo() {

		return flyingTo;

	}

	public void setFlyingTo(Airport flyingTo) {

		this.flyingTo = flyingTo;

	}

	public Airline getAirline() {

		return airline;

	}

	public void setAirline(Airline airline) {

		this.airline = airline;

	}

	

}
