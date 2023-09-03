package com.user_service.services.impl;

import java.util.Arrays;
import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;


import com.user_service.entities.Hotel;
import com.user_service.entities.Rating;
import com.user_service.entities.User;
import com.user_service.exception.ResourceNotFoundException;
import com.user_service.external.service.HotelService;
import com.user_service.repositories.UserRepository;
import com.user_service.services.Userservice;
@Service
public class UserServiceImpl  implements Userservice{

	@Autowired
	private UserRepository userRepository;
	@Autowired
	private RestTemplate restTemplate;
	
	@Autowired
	private HotelService hotelService;
	
	private org.slf4j.Logger logger=org.slf4j.LoggerFactory.getLogger(UserServiceImpl.class);
	
	@Override
	public User addUser(User user) {
		// TODO Auto-generated method stub
		String randomUserId=UUID.randomUUID().toString();
		user.setUserId(randomUserId);
		return userRepository.save(user);
	}

	
	@Override
	public List<User> getAllUser() {
		// TODO Auto-generated method stub
		return userRepository.findAll();
	}

	@Override
	public User getUser(String userId) {
		User user= userRepository.findById(userId).orElseThrow(()-> new ResourceNotFoundException("User with given id is not found on server !!" +userId));
		//fetch rating of the above user from Rating Service
		 Rating[] ratingOfUser=restTemplate.getForObject("http://RATING-SERVICE/ratings/user/"+user.getUserId(),Rating[].class);
	      logger.info("{}",ratingOfUser);
	      List<Rating> ratings=Arrays.stream(ratingOfUser).toList();
	      List<Rating>ratingList=ratings.stream().map(rating->{
	    	  //api call by declarative approch 
//	    	  ResponseEntity<Hotel>forEntity=restTemplate.getForEntity("http://HOTEL-SERVICE/hotels/"+rating.getHotelId(),Hotel.class);
	    	  Hotel hotel=hotelService.gethotel(rating.getHotelId());
//	     	  logger.info("response status code:{}",forEntity.getStatusCode());
	    	  //set the hotel to rating
	    	  rating.setHotel(hotel);
	    	  //return to rating
	    	  return rating;
	      }).collect(Collectors.toList());
	      user.setRatings(ratingList);
		 return user;
	}

	@Override
	public User updateUser(User user) {
		// TODO Auto-generated method stub
		return userRepository.save(user);
	}

	@Override
	public void deleteUser(String userId) {
		// TODO Auto-generated method stub
		User entity=userRepository.getOne(userId);
		userRepository.delete(entity);
		
	}

}
