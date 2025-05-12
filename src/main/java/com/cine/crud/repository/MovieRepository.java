package com.cine.crud.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.cine.crud.entity.Movie;

public interface MovieRepository extends JpaRepository<Movie, Long> {

	List<Movie> findAllByOrderByTitleAsc();
}
