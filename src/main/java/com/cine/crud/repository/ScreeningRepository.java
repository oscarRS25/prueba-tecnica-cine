package com.cine.crud.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.cine.crud.entity.Screening;

import jakarta.transaction.Transactional;

public interface ScreeningRepository extends JpaRepository<Screening, Long>{

	@Query("SELECT s FROM Screening s WHERE s.cinema.id = :cinemaId ORDER BY s.active DESC, s.date ASC, s.time ASC")
	List<Screening> getScreeningsByCinema(@Param("cinemaId") Long cinemaId);
	
	@Modifying
    @Transactional
    @Query("UPDATE Screening s SET s.active = NOT s.active WHERE s.id = :id")
    int toggleScreeningState(@Param("id") Long id);
	
}
