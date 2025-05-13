package com.cine.crud.service;

import java.util.List;

import com.cine.crud.entity.Screening;


public interface ScreeningService {
	
	List<Screening>getAllScreenings(Long cinemaId);
	
	Screening getScreeningById(Long id);
	
	Screening saveScreening(Screening screening);
	
	void deleteScreening(Long id);
	
	boolean toggleScreeningState(Long id);
}
