package com.criticalreview.valoracion.repository;

import org.springframework.data.mongodb.repository.Query;
import org.springframework.data.mongodb.repository.ReactiveMongoRepository;

import com.criticalreview.valoracion.model.Valoracion;

import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

public interface ValoracionRepository extends ReactiveMongoRepository<Valoracion, Integer> {

}
