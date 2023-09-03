package com.Rating_Service.services;

import java.util.List;

import org.springframework.stereotype.Service;

import com.Rating_Service.entities.Rating;

@Service
public interface RatingService {
  
	
	//create 
	Rating create(Rating rating);
	
	//get all  rating
	
	List<Rating> getRatings();
	
	//get all by userID
	
	public List<Rating> getRatingByUserId(String userId);
	//get all by hotelId
	
	public List<Rating> getRatingByHotelId(String hotelId);
	
	
}
