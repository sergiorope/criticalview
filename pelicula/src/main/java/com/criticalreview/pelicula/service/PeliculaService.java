package com.criticalreview.pelicula.service;

import com.criticalreview.pelicula.model.Pelicula;

import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

public interface PeliculaService {
	
	

	Flux<Pelicula> obtenerPeliculas();

	Mono<Pelicula> obtenerPorId(int id);
	
	Flux<Pelicula> obtenerPorMedia(double media);

	Mono<Pelicula> altaPelicula(Pelicula pelicula);

	Mono<Pelicula> actualizarPelicula(int id, Pelicula pelicula);

	Mono<Void> eliminarPelicula(int id);

}
