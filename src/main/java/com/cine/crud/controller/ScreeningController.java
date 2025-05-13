package com.cine.crud.controller;

import java.util.Collections;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.cine.crud.entity.Cinema;
import com.cine.crud.entity.Screening;
import com.cine.crud.service.CinemaService;
import com.cine.crud.service.MovieService;
import com.cine.crud.service.ScreeningService;

import jakarta.validation.Valid;

@Controller
@RequestMapping("/screening")
public class ScreeningController {

	@Autowired
	private ScreeningService screeningService;
	
	@Autowired
	private CinemaService cinemaService;
	
	@Autowired 
	private MovieService movieService;
	
	@GetMapping("/{cinemaId}")
	public String getAllScreenings(Model model, @PathVariable Long cinemaId) {
		List<Screening> screenings = screeningService.getAllScreenings(cinemaId);
		model.addAttribute("screening", screenings);
		return "screening/list";
	}
	
	@GetMapping("/edit/{id}")
	public String editScreening(@PathVariable Long id, Model model) {
		Optional<Screening> screening = Optional.ofNullable(screeningService.getScreeningById(id));
		if(screening.isPresent()) {
			model.addAttribute("screening", screening.get());
			model.addAttribute("movies", movieService.getAllMovies());
			return "screening/form";
		}else {
			return "screening/list";
		}
	}
	
	@GetMapping("/new/{cinemaId}")
	public String createScreening(@PathVariable Long cinemaId, Model model) {
	    Screening screening = new Screening();
	    Cinema cinema = cinemaService.getCinemaById(cinemaId);
	    screening.setCinema(cinema);
	    
	    model.addAttribute("screening", screening);
	    model.addAttribute("movies", movieService.getAllMovies());
	    model.addAttribute("cinemas", Collections.singletonList(cinema));
	    return "screening/form";
	}
	
	@GetMapping("/api/{cinemaId}")
	@ResponseBody
	public List<Screening> getAllScreenings(@PathVariable Long cinemaId){
		return screeningService.getAllScreenings(cinemaId);
	}
	
	@PostMapping("/api")
	public String saveScreening(@ModelAttribute @Valid Screening screening) {
		screeningService.saveScreening(screening);
		Long cinemaId = screening.getCinema().getId();
		return "redirect:/screening/" + cinemaId;
	}

	@PostMapping("/api/toggleState/{id}")
	@ResponseBody
	public String toggleScreeningState(@PathVariable Long id) {
		screeningService.toggleScreeningState(id);
		return "Estado actualizado con éxito";
	}
	
	@DeleteMapping("/api/{id}")
	@ResponseBody
	public String deleteScreening(@PathVariable Long id) {
		screeningService.deleteScreening(id);
		return "Función eliminada con éxito";
	}
}
