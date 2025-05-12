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

import com.cine.crud.entity.Movie;
import com.cine.crud.service.MovieService;

import jakarta.validation.Valid;

@RestController
@RequestMapping("/movie")
public class MovieController {

	@Autowired
	private MovieService movieService;
	
	@GetMapping
	public List<Movie> getAllMovies(){
		return movieService.getAllMovies();
	}
	
	@GetMapping("/{id}")
	public Movie getMovieById(@PathVariable Long id) {
		return movieService.getMovieById(id);
	}
	
	@PostMapping
	public Movie registerMovie(@RequestBody @Valid Movie movie) {
		return movieService.createMovie(movie);
	}
	
	@PutMapping("/{id}")
	public Movie updateMovie(@PathVariable Long id, @RequestBody @Valid Movie movie) {
		return movieService.updateMovie(id, movie);
	}
	
	@DeleteMapping("/{id}")
	public void deleteMovie(@PathVariable Long id) {
		movieService.deleteMovie(id);
	}
}
