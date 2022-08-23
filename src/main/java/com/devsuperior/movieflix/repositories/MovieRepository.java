package com.devsuperior.movieflix.repositories;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.devsuperior.movieflix.entities.Genre;
import com.devsuperior.movieflix.entities.Movie;
import com.devsuperior.movieflix.entities.Review;

public interface MovieRepository extends JpaRepository<Movie, Long> {

	@Query("SELECT obj FROM Movie obj JOIN FETCH obj.genre WHERE obj IN :movies ")
	List<Movie> findGenres(List<Movie> movies);
	
	@Query("SELECT obj FROM Review obj JOIN FETCH obj.movie m WHERE obj IN :reviews ")
	List<Review> findReviews(List<Review> reviews);

	//@Query("SELECT obj, gen FROM Movie obj INNER JOIN obj.genre gen ")
	@Query("SELECT obj "
	      + " FROM Movie obj INNER JOIN obj.genre gen "
		  + "WHERE (COALESCE(:genres) IS NULL OR gen IN :genres) ")
	Page<Movie> find(List<Genre> genres, Pageable pageable);
	
}