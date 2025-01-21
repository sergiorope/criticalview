package com.criticalreview.valoracion.repository;

import org.springframework.data.mongodb.repository.Query;
import org.springframework.data.mongodb.repository.ReactiveMongoRepository;

import com.criticalreview.valoracion.model.Valoracion;

import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

public interface ValoracionRepository extends ReactiveMongoRepository<Valoracion, Integer> {
	
	@Query("{'usuario.id': ?0}") 
    Flux<Valoracion> findByUsuario_Id(int usuarioId);
	@Query("{'pelicula.id': ?0}") 
    Flux<Valoracion> findByPelicula_Id(int peliculaId);
	
	@Query(value = "{ 'pelicula_Id': ?0 }", count = true)
	Mono<Long> countByPelicula_Id(int peliculaId);
	

}
