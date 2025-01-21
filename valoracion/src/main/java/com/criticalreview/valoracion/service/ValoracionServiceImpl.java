package com.criticalreview.valoracion.service;

import java.util.concurrent.CompletableFuture;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.kafka.support.SendResult;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;

import com.criticalreview.valoracion.model.PeliculaDTO;
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
    

	@Value("${topic}")
	String topico;
    
    @Autowired
	KafkaTemplate<String, Valoracion> kafkaTemplate;


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
	public Flux<Valoracion> obtenerValoracionesPorUsuario(int id) {
		
    	return valoracionRepository.findByUsuario_Id(id);
	}
    
    @Override
	public Flux<Valoracion> obtenerValoracionesPorPelicula(int id) {
		// TODO Auto-generated method stub
		return valoracionRepository.findByPelicula_Id(id);
	}

    @Override
    public Mono<Valoracion> altaValoracion(Valoracion valoracion) {
       
        return (valoracion.getId() == 0 
                ? sequenceGeneratorService.generateSequence(Valoracion.class.getSimpleName())
                    .map(id -> {
                        valoracion.setId(id);
                        return valoracion;
                    }) 
                : Mono.just(valoracion))
            .flatMap(valoracionRepository::save) 
            .doOnSuccess(v -> {
        
                CompletableFuture<SendResult<String, Valoracion>> future = 
                    kafkaTemplate.send(topico, v);
                
                future.whenCompleteAsync((r, t) -> {
                    if (t != null) {
                        
                        System.err.println("Error al enviar la valoración al tópico: " + t.getMessage());
                    } else {
                 
                        System.out.println("Se ha registrado la valoración " 
                            + r.getProducerRecord().value().getNota() 
                            + " en el tópico " + r.getRecordMetadata().topic());
                    }
                });
            });
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
    	
    	return valoracionRepository.findById(id).flatMap(v ->  	
         webClientBuilder
            .baseUrl("http://microservicio-usuario/usuarios") 
            .build()
            .get()
            .uri("/{id}", v.getUsuario_Id()) 
            .retrieve()
            .bodyToMono(UsuarioDTO.class));
    }

	@Override
	public Mono<PeliculaDTO> obtenerPeliculaPorValoracion(int id) {
		
		return valoracionRepository.findById(id).flatMap(v ->  	
        webClientBuilder
           .baseUrl("http://microservicio-pelicula/peliculas") 
           .build()
           .get()
           .uri("/{id}", v.getPelicula_Id()) 
           .retrieve()
           .bodyToMono(PeliculaDTO.class));
	}

	@Override
	public Mono<Long> countValoraciones(int id) {
		
		return valoracionRepository.countByPelicula_Id(id);
		
		
	}

	

	
    
    
    
    
    
}
