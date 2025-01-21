package com.criticalreview.valoracion.controller;

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
import org.springframework.web.bind.annotation.RestController;

import com.criticalreview.valoracion.model.PeliculaDTO;
import com.criticalreview.valoracion.model.UsuarioDTO;
import com.criticalreview.valoracion.model.Valoracion;
import com.criticalreview.valoracion.service.ValoracionService;

import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@RestController
public class ValoracionController {

	@Autowired
	ValoracionService valoracionService;


    @GetMapping(value = "/valoraciones/list")
	public ResponseEntity<Flux<Valoracion>> obtenerValoraciones() {

		return new ResponseEntity<>(valoracionService.obtenerValoraciones(), HttpStatus.OK);
	}


	@GetMapping(value = "valoraciones/{id}")
	public Mono<ResponseEntity<Valoracion>> obtenerValoracionPorId(@PathVariable("id") int id) {

		return valoracionService.obtenerPorId(id)
				.map(v -> ResponseEntity.ok(v))
				.defaultIfEmpty(ResponseEntity.notFound().build());

	}
	
	@GetMapping(value = "valoraciones/usuario/{id}")
	public Flux<ResponseEntity<Valoracion>> obtenerValoracionPorUsuarioId(@PathVariable("id") int id) {

		return valoracionService.obtenerValoracionesPorUsuario(id)
				.map(v -> ResponseEntity.ok(v))
				.defaultIfEmpty(ResponseEntity.notFound().build());

	}
	
	
	@GetMapping(value = "valoraciones/pelicula/{id}")
	public Flux<ResponseEntity<Valoracion>> obtenerValoracionPorPeliculaId(@PathVariable("id") int id) {

		return valoracionService.obtenerValoracionesPorPelicula(id)
				.map(v -> ResponseEntity.ok(v))
				.defaultIfEmpty(ResponseEntity.notFound().build());

	}
	

	@PostMapping(value = "valoraciones/alta", consumes = MediaType.APPLICATION_JSON_VALUE)
	public Mono<ResponseEntity<Valoracion>> altaValoracion(@RequestBody Valoracion valoracion) {

		return valoracionService.altaValoracion(valoracion)
				.map(v -> ResponseEntity.ok(v));

	}

	@PutMapping(value = "valoraciones/actualizar/{id}")
	public Mono<ResponseEntity<Valoracion>> actualizarValoracion(@PathVariable("id") int id, @RequestBody Valoracion valoracion) {

		return valoracionService.actualizarValoracion(id, valoracion)
				.map(v -> ResponseEntity.ok(v))
				.defaultIfEmpty(ResponseEntity.notFound().build());

	}

	@DeleteMapping(value = "valoraciones/eliminar/{id}")
	public Mono<ResponseEntity<Void>> borrarValoracion(@PathVariable("id") int id) {

		return valoracionService.eliminarValoracion(id)
				.map(v -> ResponseEntity.ok(v))
				.defaultIfEmpty(ResponseEntity.notFound().build());

	}
	
	@GetMapping(value = "/valoraciones/{valoracionId}/usuario")
	public Mono<ResponseEntity<UsuarioDTO>> obtenerUsuarioPorValoracion(@PathVariable("valoracionId") int valoracionId) {
	    return valoracionService.obtenerUsuarioPorValoracion(valoracionId)
	            .map(u -> ResponseEntity.ok(u))
	            .defaultIfEmpty(ResponseEntity.notFound().build());
	}
	
	@GetMapping(value = "valoraciones/{valoracionId}/pelicula")
	public Mono<ResponseEntity<PeliculaDTO>> obtenerPeliculaPorValoracion(@PathVariable("valoracionId") int id) {

		return valoracionService.obtenerPeliculaPorValoracion(id)
				.map(p -> ResponseEntity.ok(p))
				.defaultIfEmpty(ResponseEntity.notFound().build());

	}
	
	@GetMapping(value = "valoraciones/count/{peliculaId}")
	public Mono<ResponseEntity<Long>> obtenerNumeroValoracionesPelicula(@PathVariable("peliculaId") int id) {

		return valoracionService.countValoraciones(id)
				.map(l -> ResponseEntity.ok(l))
				.defaultIfEmpty(ResponseEntity.notFound().build());

	}
	
	

}
