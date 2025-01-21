package com.criticalreview.valoracion.service;


import com.criticalreview.valoracion.model.PeliculaDTO;
import com.criticalreview.valoracion.model.UsuarioDTO;
import com.criticalreview.valoracion.model.Valoracion;

import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

public interface ValoracionService {

	Flux<Valoracion> obtenerValoraciones();

	Mono<Valoracion> obtenerPorId(int id);
	
	Mono<UsuarioDTO> obtenerUsuarioPorValoracion(int id);
	
	Flux<Valoracion> obtenerValoracionesPorUsuario(int id);
	
	Flux<Valoracion> obtenerValoracionesPorPelicula(int id);

	
	Mono<PeliculaDTO> obtenerPeliculaPorValoracion(int id);


	Mono<Valoracion> altaValoracion(Valoracion valoracion);

	Mono<Valoracion> actualizarValoracion(int id, Valoracion valoracion);

	Mono<Void> eliminarValoracion(int id);
	
	Mono<Long> countValoraciones(int id);
	

}
