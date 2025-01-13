package com.criticalreview.pelicula.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.criticalreview.pelicula.model.Pelicula;
import com.criticalreview.pelicula.service.PeliculaService;

import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@RestController
public class PeliculaController {

	@Autowired
	PeliculaService peliculaService;


    @GetMapping(value = "/peliculas/list")
	public ResponseEntity<Flux<Pelicula>> obtenerPeliculaso() {

		return new ResponseEntity<>(peliculaService.obtenerPeliculas(), HttpStatus.OK);
	}


	@GetMapping(value = "peliculas/{id}")
	public Mono<ResponseEntity<Pelicula>> obtenerPeliculaPorId(@PathVariable("id") int id) {

		return peliculaService.obtenerPorId(id)
				.map(p -> ResponseEntity.ok(p))
				.defaultIfEmpty(ResponseEntity.notFound().build());

	}

	@GetMapping(value = "peliculas/media")
	public ResponseEntity<Flux<Pelicula>> obtenerPeliculasPorMedia(@RequestParam("media") double media) {

		return new ResponseEntity<>(peliculaService.obtenerPorMedia(media), HttpStatus.OK);
	}
	

	@PostMapping(value = "peliculas/alta", consumes = MediaType.APPLICATION_JSON_VALUE)
	public Mono<ResponseEntity<Pelicula>> altaPelicula(@RequestBody Pelicula pelicula) {

		return peliculaService.altaPelicula(pelicula)
				.map(p -> ResponseEntity.ok(p));

	}

	@PutMapping(value = "peliculas/actualizar/{id}")
	public Mono<ResponseEntity<Pelicula>> actualizarPelicula(@PathVariable("id") int id, @RequestBody Pelicula pelicula) {

		return peliculaService.actualizarPelicula(id, pelicula)
				.map(p -> ResponseEntity.ok(p))
				.defaultIfEmpty(ResponseEntity.notFound().build());

	}

	@DeleteMapping(value = "peliculas/eliminar/{id}")
	public Mono<ResponseEntity<Void>> borrarPelicula(@PathVariable("id") int id) {

		return peliculaService.eliminarPelicula(id)
				.map(p -> ResponseEntity.ok(p))
				.defaultIfEmpty(ResponseEntity.notFound().build());

	}

}
