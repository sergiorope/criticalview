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
    private PeliculaRepository peliculaRepository;

    @Autowired
    private ReactiveSequenceGeneratorService sequenceGeneratorService;

    @Override
    public Flux<Pelicula> obtenerPeliculas() {
        return peliculaRepository.findAll();
    }

    @Override
    public Mono<Pelicula> obtenerPorId(int id) {
        return peliculaRepository.findById(id);
    }

    @Override
    public Flux<Pelicula> obtenerPorMedia(double media) {
        return peliculaRepository.findByMedia(media);
    }

    @Override
    public Mono<Pelicula> altaPelicula(Pelicula pelicula) {

        return (pelicula.getId() == 0 ? sequenceGeneratorService.generateSequence(Pelicula.class.getSimpleName())
                .map(id -> {
                    pelicula.setId(id);
                    return pelicula;
                }) : Mono.just(pelicula))
                .flatMap(peliculaRepository::save);
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
        return obtenerPorId(id).flatMap(peliculaRepository::delete);
    }
}
