package com.capg.service;

import java.math.BigInteger;


import java.util.Optional;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.capg.dao.UserDao;
import com.capg.exception.RecordAlreadyPresentException;
import com.capg.exception.RecordNotFoundException;
import com.capg.model.Users;


@Service
public class UserServiceImpl implements UserService {

	@Autowired
	UserDao userDao;
	
	@Override
	public ResponseEntity<Users> createUser(Users newUser) {
	
		Optional<Users> findUserById = userDao.findById(newUser.getUserId());
		try {
			if (!findUserById.isPresent()) {
				userDao.save(newUser);
				return new ResponseEntity<Users>(newUser, HttpStatus.OK);
			} else
				throw new RecordAlreadyPresentException(
						"User with Id: " + newUser.getUserId() + " already exists!!");
		} catch (RecordAlreadyPresentException e) {

			return new ResponseEntity<Users>(HttpStatus.NOT_FOUND);
		}
	}

	@Override
	public Users updateUser(Users updateUser) {
	
		Optional<Users> findUserById = userDao.findById(updateUser.getUserId());
		if (findUserById.isPresent()) {
			userDao.save(updateUser);
		} else
			throw new RecordNotFoundException(
					"User with Id: " + updateUser.getUserId() + " not exists!!");
		return updateUser;
	}

	@Override
	public String deleteUser(BigInteger UserId) {
	
		Optional<Users> findBookingById = userDao.findById(UserId);
		if (findBookingById.isPresent()) {
			userDao.deleteById(UserId);
			return "User Deleted!!";
		} else
			throw new RecordNotFoundException("User not found for the entered UserID");
	}

	@Override
	public Iterable<Users> displayAllUser() {
		
		return userDao.findAll();
	}

	@Override
	public ResponseEntity<Users> findUserById(BigInteger userId) {
		
		Optional<Users> findById = userDao.findById(userId);
	
			if (findById.isPresent()) {
				Users findUser = findById.get();
				return new ResponseEntity<Users>(findUser, HttpStatus.OK);
			} else
				throw new RecordNotFoundException("No record found with ID " + userId);
		}
	}
 

 
