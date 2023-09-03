 
package com.hotel.service.impl;

import java.util.List;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.hotel.entites.Hotel;
import com.hotel.exception.ResourceNotFoundException;
import com.hotel.repositories.HotelRepositories;
import com.hotel.service.HotelService;

@Service
public class HotelServiceImpl implements HotelService {

	@Autowired
	private HotelRepositories hotelRepositories;
	
	@Override
	public Hotel create(Hotel hotel) {
		String hotelId=UUID.randomUUID().toString();
		hotel.setId(hotelId);
		return hotelRepositories.save(hotel);
	}
	

	@Override
	public List<Hotel> getAll() {
		// TODO Auto-generated method stub
		return hotelRepositories.findAll();
	}

	@Override
	public Hotel get(String id) {
		// TODO Auto-generated method stub
		return hotelRepositories.findById(id).orElseThrow(()->new ResourceNotFoundException("hotel with given id is not found !!"));
	}
    
	@Override
	public void delete(String hotelId) {
		 Hotel entity=hotelRepositories.getOne(hotelId);
		 hotelRepositories.delete(entity);
		 
	}


	@Override
	public Hotel update(Hotel hotel) {
		// TODO Auto-generated method stub
		return hotelRepositories.save(hotel);
	}
    
}
