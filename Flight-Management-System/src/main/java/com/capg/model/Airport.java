package com.capg.model;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.OneToOne;

import lombok.Data;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Entity
@Data
@ToString
public class Airport {
	
	
	@Id
	private String airportCode;
	
	private String airportName;
	
	private String airportLocation;
	
	
	public Airport() {
		
		
	}
	@OneToOne(cascade = CascadeType.ALL)
	private Schedule schedule;
	
	
}
