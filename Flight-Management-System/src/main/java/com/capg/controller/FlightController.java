package com.capg.controller;

import java.math.BigInteger;

import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.http.ResponseEntity;

import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import exceptions.RecordAlreadyPresentException;
import exceptions.RecordNotFoundException;
import com.capg.model.Flight;
//import com.capg.service.FlightService;
import com.capg.service.FlightServiceImpl;

@RestController
@RequestMapping("/flight")
public class FlightController {
	@Autowired
	private FlightServiceImpl flightservice;

	@PostMapping("/addFlight")
	@ExceptionHandler(RecordAlreadyPresentException.class)
	public void addFlight(@RequestBody Flight flight) {
		flightservice.addFlight(flight);
	}

	@GetMapping("/allFlight")
	public Iterable<Flight> viewAllFlight() {
		return flightservice.viewAllFlight();
	}

	@GetMapping("/viewFlight/{id}")
	@ExceptionHandler(RecordNotFoundException.class)
	public Flight viewFlight(@PathVariable("id") BigInteger flightNo) {
		return flightservice.viewFlight(flightNo);
	}

	@PutMapping("/updateFlight")
	@ExceptionHandler(RecordNotFoundException.class)
	public void modifyFlight(@RequestBody Flight flight) {
		flightservice.modifyFlight(flight);
	}

	@DeleteMapping("/deleteFlight/{id}")
	@ExceptionHandler(RecordNotFoundException.class)
	public void removeFlight(@PathVariable("id") BigInteger flightNo) 
	{
		flightservice.removeFlight(flightNo);
	}
}
