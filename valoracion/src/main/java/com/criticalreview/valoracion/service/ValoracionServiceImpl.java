package com.criticalreview.valoracion.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;

import com.criticalreview.valoracion.model.UsuarioDTO;
import com.criticalreview.valoracion.model.Valoracion;
import com.criticalreview.valoracion.repository.ValoracionRepository;

import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@Service
public class ValoracionServiceImpl implements ValoracionService {

    @Autowired
    private ValoracionRepository valoracionRepository;
    
    @Autowired
    private WebClient.Builder webClientBuilder;

    @Autowired
    private ReactiveSequenceGeneratorService sequenceGeneratorService;

    @Override
    public Flux<Valoracion> obtenerValoraciones() {
        return valoracionRepository.findAll();
    }

    @Override
    public Mono<Valoracion> obtenerPorId(int id) {
        return valoracionRepository.findById(id);
    }

    @Override
    public Mono<Valoracion> altaValoracion(Valoracion valoracion) {

        return (valoracion.getId() == 0 ? sequenceGeneratorService.generateSequence(Valoracion.class.getSimpleName())
                .map(id -> {
                	valoracion.setId(id);
                    return valoracion;
                }) : Mono.just(valoracion))
                .flatMap(valoracionRepository::save);
    }

    @Override
    public Mono<Valoracion> actualizarValoracion(int id, Valoracion valoracion) {
        return obtenerPorId(id).flatMap(v -> {
            v.setNota(valoracion.getNota());
            v.setFecha(valoracion.getFecha());
            v.setPelicula_Id(valoracion.getPelicula_Id());
            v.setUsuario_Id(valoracion.getUsuario_Id());
    
            return valoracionRepository.save(v);
        });
    }

    @Override
    public Mono<Void> eliminarValoracion(int id) {
        return obtenerPorId(id).flatMap(valoracionRepository::delete);
    }

    @Override
    public Mono<UsuarioDTO> obtenerUsuarioPorValoracion(int id) {
        return webClientBuilder
            .baseUrl("http://microservicio-usuario/usuarios") 
            .build()
            .get()
            .uri("/{id}", id) 
            .retrieve()
            .bodyToMono(UsuarioDTO.class);
    }
    
    
    
    
    
}
