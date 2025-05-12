package com.cine.crud.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.cine.crud.entity.Cinema;

public interface CinemaRepository extends JpaRepository<Cinema, Long>{
	
	List<Cinema> findAllByOrderByNameAsc();
	
}
