package com.capg.dao;

import java.math.BigInteger;


import org.springframework.data.repository.CrudRepository;

import com.capg.model.Users;

public interface UserDao  extends CrudRepository<Users, BigInteger>{ 

}
