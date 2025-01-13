package com.criticalreview.valoracion.service;


import com.criticalreview.valoracion.model.Valoracion;

import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

public interface ValoracionService {

	Flux<Valoracion> obtenerValoraciones();

	Mono<Valoracion> obtenerPorId(int id);

	Mono<Valoracion> altaValoracion(Valoracion valoracion);

	Mono<Valoracion> actualizarValoracion(int id, Valoracion valoracion);

	Mono<Void> eliminarValoracion(int id);

}
