package com.cine.crud.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.cine.crud.entity.Cinema;
import com.cine.crud.repository.CinemaRepository;
import com.cine.crud.service.CinemaService;

@Service
public class CinemaServiceImpl implements CinemaService{

	@Autowired
	private CinemaRepository cinemaRepository;
	
	@Override
	public List<Cinema> getAllCinemas() {
		return cinemaRepository.findAllByOrderByNameAsc();
	}

	@Override
	public Cinema getCinemaById(Long id) {
		return cinemaRepository.findById(id).orElse(null);
	}

	@Override
	public Cinema saveCinema(Cinema cinema) {
		return cinemaRepository.save(cinema);
	}

	@Override
	public void deleteCinema(Long id) {
		cinemaRepository.deleteById(id);
	}

}
