package com.cine.crud.service;

import java.util.List;

import com.cine.crud.entity.Screening;


public interface ScreeningService {

	List<Screening> getAllScreening();
	
	List<Screening>getScreeningsByCinema(Long id_cinema);
	
	Screening getScreeningById(Long id);
	
	Screening createScreening(Screening screening);
	
	Screening updateScreening(Long id, Screening screening);
	
	void deleteScreening(Long id);
	
	boolean toggleScreeningState(Long id);
}
