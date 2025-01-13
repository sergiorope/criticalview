package com.criticalreview.pelicula.repository;

import org.springframework.data.mongodb.repository.Query;
import org.springframework.data.mongodb.repository.ReactiveMongoRepository;

import com.criticalreview.pelicula.model.Pelicula;

import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

public interface PeliculaRepository extends ReactiveMongoRepository<Pelicula, Integer> {
	
	@Query(value = "{'media': ?0}")
    Flux<Pelicula> findByMedia(double media);
	
	
	
	

}
