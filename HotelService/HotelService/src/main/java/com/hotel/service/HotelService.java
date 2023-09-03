package com.hotel.service;

import java.util.List;

import com.hotel.entites.Hotel;

public interface HotelService {

	
	//create
	Hotel create(Hotel hotel);
	
	//get all
	List<Hotel> getAll();
	
	//get Single
	Hotel get(String id);
	
	//update
	Hotel update(Hotel hotel);
	
	//delete
	void delete(String hotelId);
	
}
