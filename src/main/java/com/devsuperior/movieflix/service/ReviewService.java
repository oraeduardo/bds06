package com.devsuperior.movieflix.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.devsuperior.movieflix.dto.ReviewDTO;
import com.devsuperior.movieflix.entities.Movie;
import com.devsuperior.movieflix.entities.Review;
import com.devsuperior.movieflix.entities.User;
import com.devsuperior.movieflix.repositories.ReviewRepository;

@Service
public class ReviewService {

	@Autowired
	ReviewRepository repository;
	
	@Autowired
	private AuthService authService;
	
	@Transactional
	public ReviewDTO insert(ReviewDTO dto) {
		Review entity = new Review();
		copyDtoToEntity(dto, entity);
		entity = repository.save(entity);
		return new ReviewDTO(entity); 
	}
	
	private void copyDtoToEntity(ReviewDTO dto, Review entity) {
		
		User user = authService.authenticated();

		Movie movie = new Movie();
		movie.setId(dto.getMovieId());
		
		entity.setText(dto.getText());
		entity.setMovie(movie);
		entity.setUser(user);
		
	}	
	
}
