package com.hotel.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
 
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.hotel.entites.Hotel;
import com.hotel.service.HotelService;


@RestController
@RequestMapping("/hotels")
public class HotelController {
    @Autowired
	private HotelService hotelService;
	//create 
    @PreAuthorize("hasAuthority('Admin')")
    @PostMapping
	public ResponseEntity<Hotel> createhotel(@RequestBody Hotel hotel){
		return ResponseEntity.status(HttpStatus.CREATED).body(hotelService.create(hotel));
	}
	
	//get single
    @PreAuthorize("hasAuthority('SCOPE_internal')")
    @GetMapping("/{hotelId}")
    public ResponseEntity<Hotel>getHotel(@PathVariable String hotelId){
    	return ResponseEntity.status(HttpStatus.OK).body(hotelService.get(hotelId));
    }
    //get all
    @GetMapping
    @PreAuthorize("hasAuthority('Admin')|| hasAuthority('SCOPE_internal')")
    public ResponseEntity<List<Hotel>> getAllHotel(){
    	return ResponseEntity.ok(hotelService.getAll());
    }
    //update
    @PreAuthorize("hasAuthority('Admin')")
    @PutMapping
    public ResponseEntity<Hotel>updateHotel(@RequestBody Hotel hotel){
    	return ResponseEntity.status(HttpStatus.CREATED).body(hotelService.update(hotel));
    }
    //delete
    @PreAuthorize("hasAuthority('Admin')")
    @DeleteMapping("/{hotelId}")
    public void deleteUser(@PathVariable String hotelId) {
    		hotelService.delete(hotelId);
    	}
    }

