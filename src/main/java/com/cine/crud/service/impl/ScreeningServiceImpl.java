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
	public List<Screening> getAllScreening() {
		return screeningRepository.findAll();
	}

	@Override
	public Screening getScreeningById(Long id) {
		return screeningRepository.findById(id).orElse(null);
	}

	@Override
	public Screening createScreening(Screening screening) {
		return screeningRepository.save(screening);
	}

	@Override
	public Screening updateScreening(Long id, Screening screening) {
		if(screeningRepository.existsById(id)) {
			screening.setId(id);
			return screeningRepository.save(screening);
		}
		return null;
	}

	@Override
	public void deleteScreening(Long id) {
		screeningRepository.deleteById(id);
	}

	@Override
	public boolean toggleScreeningState(Long id) {
		return screeningRepository.toggleScreeningState(id) > 0;
	}

	@Override
	public List<Screening> getScreeningsByCinema(Long id_cinema) {
		return screeningRepository.getScreeningsByCinema(id_cinema);
	}

}
