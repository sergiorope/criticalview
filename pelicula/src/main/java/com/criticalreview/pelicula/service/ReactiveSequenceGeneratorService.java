package com.criticalreview.pelicula.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.data.mongodb.core.query.Update;
import org.springframework.stereotype.Service;

import com.criticalreview.pelicula.model.DatabaseSequence;

import reactor.core.publisher.Mono;
import org.springframework.data.mongodb.core.ReactiveMongoTemplate;

@Service
public class ReactiveSequenceGeneratorService {

    @Autowired
    private ReactiveMongoTemplate reactiveMongoTemplate;

    public Mono<Long> generateSequence(String seqName) {
        return reactiveMongoTemplate.findAndModify(
                Query.query(Criteria.where("_id").is(seqName)),
                new Update().inc("seq", 1),
                DatabaseSequence.class
            )
            .defaultIfEmpty(new DatabaseSequence()) // En caso de que no exista, devuelve una nueva instancia
            .flatMap(sequence -> {
                if (sequence.getId() == null) {
                    sequence.setId(seqName);
                    sequence.setSeq(1L);
                    return reactiveMongoTemplate.save(sequence).thenReturn(1L);
                }
                return Mono.just(sequence.getSeq());
            });
    }
}
