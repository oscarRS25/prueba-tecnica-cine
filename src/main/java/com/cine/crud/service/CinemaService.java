package com.cine.crud.service;

import java.util.List;

import com.cine.crud.entity.Cinema;

public interface CinemaService {

	List<Cinema> getAllCinemas();
	
	Cinema getCinemaById(Long id);
	
	Cinema saveCinema(Cinema cinema);
	
	void deleteCinema(Long id);
}
