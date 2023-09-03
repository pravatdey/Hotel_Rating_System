package com.Rating_Service.repostitory;

import java.util.List;

import org.springframework.data.mongodb.repository.MongoRepository;

import com.Rating_Service.entities.Rating;

public interface RatingRepostitory extends MongoRepository<Rating, String>{

	
	 //custom finder method
	public List<Rating> findByUserId(String userId);
	public List<Rating> findByHotelId(String hotelId);
	
	
}
