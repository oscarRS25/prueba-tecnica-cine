package com.cine.crud.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.cine.crud.entity.Screening;
import com.cine.crud.repository.ScreeningRepository;
import com.cine.crud.service.ScreeningService;

@Service
public class ScreeningServiceImpl implements ScreeningService{

	@Autowired
	private ScreeningRepository screeningRepository;
	
	@Override
	public List<Screening> getAllScreenings(Long cinemaId) {
		return screeningRepository.getScreeningsByCinema(cinemaId);
	}
	
	@Override
	public Screening getScreeningById(Long id) {
		return screeningRepository.findById(id).orElse(null);
	}

	@Override
	public Screening saveScreening(Screening screening) {
		return screeningRepository.save(screening);
	}

	@Override
	public void deleteScreening(Long id) {
		screeningRepository.deleteById(id);
	}

	@Override
	public boolean toggleScreeningState(Long id) {
		return screeningRepository.toggleScreeningState(id) > 0;
	}

}
