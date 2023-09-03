 package com.Rating_Service.services.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

 
import com.Rating_Service.entities.Rating;
import com.Rating_Service.repostitory.RatingRepostitory;
import com.Rating_Service.services.RatingService;

@Service
public class RatingServiceImpl implements RatingService {
	
	
	@Autowired
	private RatingRepostitory ratingRepostitory;

	@Override
	public Rating create(Rating rating) {
		// TODO Auto-generated method stub
		return ratingRepostitory.save(rating);
	}

	@Override
	public List<Rating> getRatings() {
		// TODO Auto-generated method stub
		return ratingRepostitory.findAll();
	}

	@Override
	public List<Rating> getRatingByUserId(String userId) {
		// TODO Auto-generated method stub
		return this.ratingRepostitory.findByUserId(userId);
	}
	 

	@Override
	public List<Rating> getRatingByHotelId(String hotelId) {
		// TODO Auto-generated method stub
		return this.ratingRepostitory.findByHotelId(hotelId);
	}

}
