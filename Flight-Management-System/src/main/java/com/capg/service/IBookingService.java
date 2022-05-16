package com.capg.service;

import java.math.BigInteger;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.capg.dao.BookingDao;
import com.capg.model.Booking;

import exceptions.RecordAlreadyPresentException;
import exceptions.RecordNotFoundException;

@Service
public class IBookingService implements BookingService {
	@Autowired
	BookingDao bookingdao;

	@Override
	public ResponseEntity<Booking> createBooking(Booking newBooking) {
		Optional<Booking> findBookingById = bookingdao.findById(newBooking.getBookingId());
		try {
			if (findBookingById.isPresent()) {
				bookingdao.save(newBooking);
				return new ResponseEntity<Booking>(newBooking, HttpStatus.OK);
			} else
				throw new RecordAlreadyPresentException(
						"Booking with Booking id" + newBooking.getBookingId() + "Already Exists!");

		} catch (RecordAlreadyPresentException e) {
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		}
	}

	@Override
	public Booking updateBooking(Booking updateBooking) {
		Optional<Booking> findBookingById = bookingdao.findById(updateBooking.getBookingId());

		if (findBookingById.isPresent()) {
			bookingdao.save(updateBooking);

		} else
			throw new RecordNotFoundException("Booking with booking id" + updateBooking.getBookingId() + "Not Exists!");
		return updateBooking;

	}

	@Override
	public String deleteBooking(BigInteger bookingId) {
		Optional<Booking> findBookingId = bookingdao.findById(bookingId);
		if (findBookingId.isPresent()) {
			bookingdao.deleteById(bookingId);
			return "Booking Deleted!";

		} else
			throw new RecordNotFoundException("Booking Not Found For This BookingId!");

	}

	@Override
	public Iterable<Booking> displayAllbooking() {

		return bookingdao.findAll();
	}

	@Override
	public ResponseEntity<Booking> findBookingById(BigInteger bookingId) {
		Optional<Booking> findById = bookingdao.findById(bookingId);
		try {
			if (findById.isPresent()) {
				Booking findBooking = findById.get();
				return new ResponseEntity<Booking>(findBooking, HttpStatus.OK);
			} else
				throw new RecordNotFoundException("No record found with ID " + bookingId);
		} catch (RecordNotFoundException e) {
			return new ResponseEntity(e.getMessage(), HttpStatus.NOT_FOUND);
		}
	}
}
