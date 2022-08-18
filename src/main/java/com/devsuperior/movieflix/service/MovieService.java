package com.devsuperior.movieflix.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.devsuperior.movieflix.dto.MovieDTO;
import com.devsuperior.movieflix.entities.Movie;
import com.devsuperior.movieflix.entities.Review;
import com.devsuperior.movieflix.repositories.MovieRepository;
import com.devsuperior.movieflix.service.exceptions.ResourceNotFoundException;

@Service
public class MovieService {

	@Autowired
	private MovieRepository repository;
	
	@Transactional(readOnly = true)
	public Page<MovieDTO> findAllPaged(Long genreId, Pageable pageable) {
		Page<Movie> page = repository.find(genreId, pageable);
		return page.map(x -> new MovieDTO(x));
	}
	
	
	@Transactional(readOnly = true)
	public MovieDTO findById(Long id) {
		Optional<Movie> obj = repository.findById(id);
		Movie entity = obj.orElseThrow(() -> new ResourceNotFoundException("Entity not found"));
		return new MovieDTO(entity);
	}
	
	@Transactional(readOnly = true)
	public MovieDTO findByIdReviews(Long id) {
		Optional<Movie> obj = repository.findById(id);
		Movie entity = obj.orElseThrow(() -> new ResourceNotFoundException("Entity not found"));
		List<Review> reviews = repository.findReviews(id);
		return new MovieDTO(entity, reviews);
	}
	
	//public MovieDTO findById(Long id) {
	//	Optional<Movie> obj = repository.findById(id);
	//	Movie entity = obj.orElseThrow(() -> new ResourceNotFoundException("Entity not found"));
	//	return new MovieDTO(entity);
	//}
		//public List<MovieDTO> findByIdReviews(Long id) {
	//	//Optional<Movie> obj = repository.findByIdReviews(id);
	//	List<Movie> obj = repository.findByIdReviews(id);
	//	//Movie entity = obj.orElseThrow(() -> new ResourceNotFoundException("Entity not found"));
	//	return obj.stream().map(x -> new MovieDTO(x)).collect(Collectors.toList()); 
	//	//return new MovieDTO(obj);
	//}
	
}
