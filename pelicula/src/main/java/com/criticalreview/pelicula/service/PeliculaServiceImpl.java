package com.criticalreview.pelicula.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.criticalreview.pelicula.model.Pelicula;
import com.criticalreview.pelicula.repository.PeliculaRepository;

import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@Service
public class PeliculaServiceImpl implements PeliculaService {

	@Autowired
	PeliculaRepository peliculaRepository;

	@Override
	public Flux<Pelicula> obtenerPeliculas() {

		return peliculaRepository.findAll();
	}

	@Override
	public Mono<Pelicula> obtenerPorId(int id) {

		return peliculaRepository.findById(id);
	}
	
	@Override
	public Mono<Pelicula> obtenerPorMedia(double media) {

		return peliculaRepository.findByMedia(media);
	}

	@Override
	public Mono<Pelicula> altaPelicula(Pelicula pelicula) {

		return obtenerPorId(pelicula.getId())
				.switchIfEmpty(Mono.just(pelicula)
						.flatMap(p -> peliculaRepository.save(p)));

	}

	@Override
	public Mono<Pelicula> actualizarPelicula(int id, Pelicula pelicula) {

		return obtenerPorId(id).flatMap(p -> {

			p.setNombre(pelicula.getNombre());
			p.setDuracion(pelicula.getDuracion());
			p.setPais(pelicula.getPais());
			p.setDirector(pelicula.getDirector());
			p.setMedia(pelicula.getMedia());

			return peliculaRepository.save(p);

		});

	}

	@Override
	public Mono<Void> eliminarPelicula(int id) {

		return obtenerPorId(id)
				.flatMap(p -> peliculaRepository.delete(p));

	}

}
