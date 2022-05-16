package com.capg.model;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonFormat;

import lombok.Data;

@Entity
@Data
@Table
public class Schedule {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "schedule_Id")
	private int scheduleid;
	
	@Column(name = "departure_date")
	@JsonFormat(pattern = "dd-mm-yyyy HH:mm:ss")
	private String deptDateTime;
	
	@Column(name = "arrival_date")
	@JsonFormat(pattern =  "dd-mm-yyyy HH:mm:ss")
	private String arrDateTime;
	
	@OneToOne(cascade = CascadeType.ALL)
	@JoinColumn(name = "srcAirport_id")
	private Airport srcAirport;
	
	@OneToOne(cascade = CascadeType.ALL)
	@JoinColumn(name = "dstnAirport_id")
	private Airport dstnAirport;

	public Schedule() {
		
	}

	
	
	
}
