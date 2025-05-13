package com.cine.crud.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.cine.crud.entity.Movie;
import com.cine.crud.repository.MovieRepository;
import com.cine.crud.service.MovieService;

@Service
public class MovieServiceImpl implements MovieService{

	@Autowired
	private MovieRepository movieRepository;
	
	@Override
	public List<Movie> getAllMovies() {
		return movieRepository.findAllByOrderByTitleAsc();
	}

	@Override
	public Movie getMovieById(Long id) {
		return movieRepository.findById(id).orElse(null);
	}

	@Override
	public Movie saveMovie(Movie movie) {
		return movieRepository.save(movie);
	}
	
	@Override
	public void deleteMovie(Long id) {
		movieRepository.deleteById(id);
	}

}
