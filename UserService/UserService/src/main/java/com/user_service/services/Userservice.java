package com.user_service.services;

import java.util.List;
 
import com.user_service.entities.User;

 
public interface Userservice {

	//Create User
		User addUser(User user);
		
		//get all user
		List<User> getAllUser();
		
		//get single user
		User getUser(String userId);
		
		//update user
		User updateUser(User user);
		
		//delete user
		void deleteUser(String userId);
		
		
}
