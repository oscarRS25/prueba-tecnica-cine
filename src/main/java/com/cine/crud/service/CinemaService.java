package com.cine.crud.service;

import java.util.List;

import com.cine.crud.entity.Cinema;

public interface CinemaService {

	List<Cinema> getAllCinemas();
	
	Cinema getCinemaById(Long id);
	
	Cinema createCinema(Cinema cinema);
	
	Cinema updateCinema(Long id, Cinema cinema);
	
	void deleteCinema(Long id);
}
