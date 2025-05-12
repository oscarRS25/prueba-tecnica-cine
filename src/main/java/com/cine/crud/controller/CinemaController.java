package com.cine.crud.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.cine.crud.entity.Cinema;
import com.cine.crud.service.CinemaService;

import jakarta.validation.Valid;

@RestController
@RequestMapping("/cinema")
public class CinemaController {

	@Autowired
	private CinemaService cinemaService;
	
	@GetMapping
	public List<Cinema> getAllCinemas(){
		return cinemaService.getAllCinemas();
	}
	
	@GetMapping("/{id}")
	public Cinema getCinemaById(@PathVariable Long id) {
		return cinemaService.getCinemaById(id);
	}
	
	@PostMapping
	public Cinema registerCinema(@RequestBody @Valid Cinema cinema) {
		return cinemaService.createCinema(cinema);
	}
	
	@PutMapping("/{id}")
	public Cinema updateCinema(@PathVariable Long id, @RequestBody @Valid Cinema cinema) {
		return cinemaService.updateCinema(id, cinema);
	}
	
	@DeleteMapping("/{id}")
	public void deleteCinema(@PathVariable Long id) {
		cinemaService.deleteCinema(id);
	}
}
