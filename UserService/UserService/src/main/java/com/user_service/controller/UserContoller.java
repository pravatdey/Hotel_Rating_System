package com.user_service.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.user_service.entities.User;
import com.user_service.services.Userservice;
import com.user_service.services.impl.UserServiceImpl;

import io.github.resilience4j.circuitbreaker.annotation.CircuitBreaker;
import io.github.resilience4j.ratelimiter.annotation.RateLimiter;
import io.github.resilience4j.retry.annotation.Retry;

 
@RestController
@RequestMapping("/users")
public class UserContoller {
 
    @Autowired
	private Userservice userservice;
	private org.slf4j.Logger logger=org.slf4j.LoggerFactory.getLogger(UserContoller.class);
	//create 
	@PostMapping
	public ResponseEntity<User> creatUser(@RequestBody User user,BindingResult result){
		if(result.hasErrors()) {
			System.out.println(result);
			return ResponseEntity.status(HttpStatus.UPGRADE_REQUIRED).body(user);
		}else {
		User user1= userservice.addUser(user);
		return ResponseEntity.status(HttpStatus.CREATED).body(user1);
		}
	}
	
	 
	//single user get
	@GetMapping("/{userId}")
//	@CircuitBreaker(name="RatingHotelBreaker",fallbackMethod="ratingHotelFallback")
//	@Retry(name="RatingHotelService",fallbackMethod = "ratingHotelFallback")
	@RateLimiter(name= "userRateLimiter", fallbackMethod = "ratingHotelFallback")
	public ResponseEntity<User>getSingleUser(@PathVariable String userId){
	logger.info("Get Single User Handler: UserController");
 
	 
	User user=userservice.getUser(userId);
	return ResponseEntity.ok(user);
	}
	//Creating fallback method for Circuit Breaker
	public ResponseEntity<User> ratingHotelFallback( String userId,Exception ex){
//		logger.info("Fallback is executed because service is down :", ex.getMessage());
		
		User user=User.builder()
				.email("dummy@gmail.com")
				.name("Dummy")
				.about("This user is created by dummy because some service is down")
				.userId("121212")
				.build();
		return new ResponseEntity<>(user,HttpStatus.OK);
	}
	
	//get all user
	@GetMapping
	public ResponseEntity<List<User>> getALLUser(){
	List<User> allUser =	userservice.getAllUser();
	return ResponseEntity.ok(allUser);
	}
	@PutMapping
	public ResponseEntity<User>updateUser(@RequestBody User user,BindingResult result){
		if(result.hasErrors()) {
			return ResponseEntity.status(HttpStatus.UPGRADE_REQUIRED).body(user);
		}
		User user1=userservice.updateUser(user);
		return  ResponseEntity.status(HttpStatus.CREATED).body(user1);
	}
	@DeleteMapping("/{userId}")
	public void deleteUser(@PathVariable String userId){
		  userservice.deleteUser(userId);
	}
}
