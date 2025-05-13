package com.cine.crud.controller;

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
import com.cine.crud.service.CinemaService;

import jakarta.validation.Valid;

@Controller
@RequestMapping("/cinema")
public class CinemaController {

	@Autowired
	private CinemaService cinemaService;
	
	@GetMapping
	public String getAllCinemas(Model model) {
		List<Cinema> cinemas = cinemaService.getAllCinemas();
		model.addAttribute("cinemas", cinemas);
		return "cinema/list";
	}
	
	@GetMapping("/edit/{id}")
	public String editCinema(@PathVariable Long id, Model model) {
		Optional<Cinema> cinema = Optional.ofNullable(cinemaService.getCinemaById(id));
		if(cinema.isPresent()) {
			model.addAttribute("cinema", cinema.get());
			return "cinema/form";
		}else {
			return "redirect:/cinema";
		}
	}
	
	@GetMapping("/new")
	public String createCinema(Model model) {
		model.addAttribute("cinema", new Cinema());
		return "cinema/form";
	}
	
	
	@GetMapping("/api")
	@ResponseBody
	public List<Cinema> getAllCinemas(){
		return cinemaService.getAllCinemas();
	}
	
	@PostMapping("/api")
	public String saveCinema(@ModelAttribute @Valid Cinema cinema) {
		cinemaService.saveCinema(cinema);
		return "redirect:/cinema";
	}
	
	@DeleteMapping("/api/{id}")
	@ResponseBody
	public String deleteCinema(@PathVariable Long id) {
		cinemaService.deleteCinema(id);
		return "Cine eliminado con éxito";
	}
}
