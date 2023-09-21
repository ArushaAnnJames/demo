package com.example.flight.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

import com.fasterxml.jackson.annotation.JsonProperty;

@Entity
public class Airline {
   @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
   private String name;
   @Column(unique=true)	
    private String code;
    @JsonProperty("is_lowcost")
    private boolean isLowCost;
    private String logo;


 

	public Airline() {
		super();
		// TODO Auto-generated constructor stub
	}

 

	public Airline(Long id, String name, String code, boolean isLowCost, String logo) {
		super();
		this.id = id;
		this.name = name;
		this.code = code;
		this.isLowCost = isLowCost;
		this.logo = logo;
	}

 

	public Long getId() {
		return id;
	}

 

	public void setId(Long id) {
		this.id = id;
	}

 

	public String getName() {
		return name;
	}

 

	public void setName(String name) {
		this.name = name;
	}

 

	public String getCode() {
		return code;
	}

 

	public void setCode(String code) {
		this.code = code;
	}

 

	public boolean isLowCost() {
		return isLowCost;
	}

 

	public void setLowCost(boolean isLowCost) {
		this.isLowCost = isLowCost;
	}

 

	public String getLogo() {
		return logo;
	}

 

	public void setLogo(String logo) {
		this.logo = logo;
	}

 

	@Override
	public String toString() {
		return "AirlinesEntity [id=" + id + ", name=" + name + ", code=" + code + ", isLowCost=" + isLowCost + ", logo="
				+ logo + "]";
	}
}

