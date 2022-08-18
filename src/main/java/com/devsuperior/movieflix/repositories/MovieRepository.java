package com.devsuperior.movieflix.repositories;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.devsuperior.movieflix.entities.Movie;
import com.devsuperior.movieflix.entities.Review;

public interface MovieRepository extends JpaRepository<Movie, Long> {
	
	@Query("SELECT obj FROM Review obj INNER JOIN obj.movie m WHERE m.id = :id")
	List<Review> findReviews(Long id);

	@Query("SELECT obj FROM Movie obj WHERE (obj.genre.id = :genreId OR :genreId = 0) ")
	Page<Movie> find(Long genreId, Pageable pageable);
	
}
