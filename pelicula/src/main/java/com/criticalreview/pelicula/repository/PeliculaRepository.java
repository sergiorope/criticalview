package com.criticalreview.pelicula.repository;

import org.springframework.data.mongodb.repository.Query;
import org.springframework.data.mongodb.repository.ReactiveMongoRepository;

import com.criticalreview.pelicula.model.Pelicula;

import reactor.core.publisher.Mono;

public interface PeliculaRepository extends ReactiveMongoRepository<Pelicula, Integer> {
	
	@Query(value = "{'media':{$lt:?0}}")
	Mono<Pelicula> findByMedia(double media);
	
	
	
	

}
