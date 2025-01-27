package com.criticalreview.pelicula.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.criticalreview.pelicula.model.Pelicula;
import com.criticalreview.pelicula.model.ValoracionDTO;
import com.criticalreview.pelicula.repository.PeliculaRepository;


import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@Service
public class PeliculaServiceImpl implements PeliculaService {

    @Autowired
    private PeliculaRepository peliculaRepository;

    @Autowired
    private ReactiveSequenceGeneratorService sequenceGeneratorService;
    
    @Autowired
    private WebClient.Builder webClientBuilder;
    


    @Override
    public Flux<Pelicula> obtenerPeliculas() {
        return peliculaRepository.findAll();
    }
    
    @Override
    public Flux<Pelicula> obtenerTopPeliculas() {
        return obtenerPeliculas().sort((p1, p2) -> Double.compare(p2.getMedia(), p1.getMedia()));

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
                    pelicula.setMedia(0.0);
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
    
    
    
    @KafkaListener(topics = "valoracionesTopic", groupId = "myGroup1")
    public void gestionMediaPelicula(ValoracionDTO valoracion) {
        obtenerPorId(valoracion.getPelicula_Id())
            .flatMap(p -> 
                webClientBuilder
                    .baseUrl("http://microservicio-valoracion/valoraciones/count")
                    .build()
                    .get()
                    .uri("/{peliculaId}", p.getId())
                    .retrieve()
                    .bodyToMono(Long.class) 
                    .map(count -> {
                        if (count == 1) { 
                            p.setMedia(valoracion.getNota()); 
                        } else {
                          
                            double nuevaMediaAgregada = p.getMedia() * (count - 1);
                            double nuevaSumaAgregada = nuevaMediaAgregada + valoracion.getNota();
                            p.setMedia(nuevaSumaAgregada / count);
                        }
                        return p;
                    })
            )
            .flatMap(peliculaRepository::save)
            .subscribe();
    }

    @KafkaListener(topics = "valoracionesTopicEliminacion", groupId = "myGroup1")
    public void gestionMediaPeliculaEliminacion(ValoracionDTO valoracion) {
        obtenerPorId(valoracion.getPelicula_Id())
            .flatMap(p -> 
                webClientBuilder
                    .baseUrl("http://microservicio-valoracion/valoraciones/count")
                    .build()
                    .get()
                    .uri("/{peliculaId}", p.getId())
                    .retrieve()
                    .bodyToMono(Long.class) 
                    .map(count -> {
                        if (count == 0) {
                            p.setMedia(0); 
                        } else {
                           
                            double nuevaMediaEliminacion = p.getMedia() * (count + 1);
                            double nuevaSumaEliminacion = nuevaMediaEliminacion - valoracion.getNota();
                            p.setMedia(nuevaSumaEliminacion / count);
                        }
                        return p;
                    })
            )
            .flatMap(peliculaRepository::save)
            .subscribe();
    }



	
}
