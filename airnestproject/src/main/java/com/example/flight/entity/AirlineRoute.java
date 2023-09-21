package com.example.flight.entity;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.Map;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.PrePersist;
import javax.persistence.PreUpdate;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@JsonIgnoreProperties(ignoreUnknown = true)
public class AirlineRoute {
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private int id;

	@JsonProperty("iata_from")
	private String iataFrom;

	@JsonProperty("iata_to")
	private String iataTo;

	private String airLineIata;
	@JsonProperty("airline") // Use this annotation to specify the JSON field name
    private void unpackNestedIATA(Map<String, Object> airline) {
        this.airLineIata = (String) airline.get("IATA");
    }

	@JsonProperty("airportVia")
	private String airportVia;
	@JsonProperty("class_business")
	private boolean classBusiness;
	@JsonProperty("class_economy")
	private boolean classEconomy;
	@JsonProperty("class_first")
	private boolean classFirst;
	private String day1;
	private String day2;
	private String day3;
private String day4;
	private String day5;
	private String day6;
	private String day7;
	@JsonProperty("max_duration")
	private int maxDuration;

	@JsonProperty("min_duration")
	private int minDuration;
	@Column(name = "created_on", columnDefinition = "TIMESTAMP")
	private LocalDateTime createdOn;
	@Column(name = "updated_on", columnDefinition = "TIMESTAMP")
	private LocalDateTime updatedOn;

 

	@PrePersist
	protected void onCreate() {
		createdOn = LocalDateTime.now();
	}
	@PreUpdate
	protected void onUpdate() {
		updatedOn = LocalDateTime.now();

 

	}

 

	private String classType;
	private LocalDate date;
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getIataFrom() {
		return iataFrom;
	}
	public void setIataFrom(String iataFrom) {
		this.iataFrom = iataFrom;
	}
	public String getIataTo() {
		return iataTo;
	}
	public void setIataTo(String iataTo) {
		this.iataTo = iataTo;
	}
	public String getAirLineIata() {
		return airLineIata;
	}
	public void setAirLineIata(String airLineIata) {
		this.airLineIata = airLineIata;
	}
	public String getAirportVia() {
		return airportVia;
	}
	public void setAirportVia(String airportVia) {
		this.airportVia = airportVia;
	}
	public boolean isClassBusiness() {
		return classBusiness;
	}
	public void setClassBusiness(boolean classBusiness) {
		this.classBusiness = classBusiness;
	}
	public boolean isClassEconomy() {
		return classEconomy;
	}
	public void setClassEconomy(boolean classEconomy) {
		this.classEconomy = classEconomy;
	}
	public boolean isClassFirst() {
		return classFirst;
	}
	public void setClassFirst(boolean classFirst) {
		this.classFirst = classFirst;
	}
	public String getDay1() {
		return day1;
	}
	public void setDay1(String day1) {
		this.day1 = day1;
	}
	public String getDay2() {
		return day2;
	}
	public void setDay2(String day2) {
		this.day2 = day2;
	}
	public String getDay3() {
		return day3;
	}
	public void setDay3(String day3) {
		this.day3 = day3;
	}
	public String getDay4() {
		return day4;
	}
	public void setDay4(String day4) {
		this.day4 = day4;
	}
	public String getDay5() {
		return day5;
	}
	public void setDay5(String day5) {
		this.day5 = day5;
	}
	public String getDay6() {
		return day6;
	}
	public void setDay6(String day6) {
		this.day6 = day6;
	}
	public String getDay7() {
		return day7;
	}
	public void setDay7(String day7) {
		this.day7 = day7;
	}
	public int getMaxDuration() {
		return maxDuration;
	}
	public void setMaxDuration(int maxDuration) {
		this.maxDuration = maxDuration;
	}
	public int getMinDuration() {
		return minDuration;
	}
	public void setMinDuration(int minDuration) {
		this.minDuration = minDuration;
	}
	public LocalDateTime getCreatedOn() {
		return createdOn;
	}
	public void setCreatedOn(LocalDateTime createdOn) {
		this.createdOn = createdOn;
	}
	public LocalDateTime getUpdatedOn() {
		return updatedOn;
	}
	public void setUpdatedOn(LocalDateTime updatedOn) {
		this.updatedOn = updatedOn;
	}
	public String getClassType() {
		return classType;
	}
	public void setClassType(String classType) {
		this.classType = classType;
	}
	public LocalDate getDate() {
		return date;
	}
	public void setDate(LocalDate date) {
		this.date = date;
	}
	


}





