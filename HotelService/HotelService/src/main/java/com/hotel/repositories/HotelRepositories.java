package com.hotel.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.hotel.entites.Hotel;

public interface HotelRepositories extends JpaRepository<Hotel, String>{

	
}
