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

import com.cine.crud.entity.Screening;
import com.cine.crud.service.ScreeningService;

import jakarta.validation.Valid;

@RestController
@RequestMapping("/screening")
public class ScreeningController {

	@Autowired
	private ScreeningService screeningService;
	
	@GetMapping
	public List<Screening> getAllScreenings(){
		return screeningService.getAllScreening();
	}
	
	@GetMapping("/{id}")
	public Screening getScreeningById(@PathVariable Long id) {
		return screeningService.getScreeningById(id);
	}
	
	@GetMapping("/byCinema/{id_cinema}")
	public List<Screening> getScreeningsByCinema(@PathVariable Long id_cinema) {
		return screeningService.getScreeningsByCinema(id_cinema);
	}
	
	@PostMapping
	public Screening registerScreening(@RequestBody @Valid Screening screening) {
		return screeningService.createScreening(screening);
	}
	
	@PutMapping("/{id}")
	public Screening updateScreening(@PathVariable Long id, @RequestBody @Valid Screening screening) {
		return screeningService.updateScreening(id, screening);
	}
	
	@PutMapping("/toggleState/{id}")
	public boolean toggleScreeningState(@PathVariable Long id) {
		return screeningService.toggleScreeningState(id);
	}
	
	@DeleteMapping("/{id}")
	public void deleteScreening(@PathVariable Long id) {
		screeningService.deleteScreening(id);
	}
}
