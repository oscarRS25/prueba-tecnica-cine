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

import com.cine.crud.entity.Movie;
import com.cine.crud.service.MovieService;

import jakarta.validation.Valid;

@Controller
@RequestMapping("/movie")
public class MovieController {

	@Autowired
	private MovieService movieService;
	
	@GetMapping
	public String getAllMovies(Model model) {
		List<Movie> movies = movieService.getAllMovies();
		model.addAttribute("movies", movies);
		return "movie/list";
	}
	
	@GetMapping("/edit/{id}")
	public String editMovie(@PathVariable Long id, Model model) {
		Optional<Movie> movie = Optional.ofNullable(movieService.getMovieById(id));
		if(movie.isPresent()) {
			model.addAttribute("movie", movie.get());
			return "movie/form";
		}else {
			return "redirect:/movie";
		}
	}
	
	@GetMapping("/new")
	public String createMovie(Model model) {
		model.addAttribute("movie", new Movie());
		return "movie/form";
	}
	
	
	@GetMapping("/api")
	@ResponseBody
	public List<Movie> getAllMovies(){
		return movieService.getAllMovies();
	}
	
	@PostMapping("/api")
	public String saveMovie(@ModelAttribute @Valid Movie movie) {
		movieService.saveMovie(movie);
		return "redirect:/movie";
	}
	
	@DeleteMapping("/api/{id}")
	@ResponseBody
	public String deleteMovie(@PathVariable Long id) {
		movieService.deleteMovie(id);
		return "Película eliminada con éxito";
	}
}
