package com.criticalreview.usuario.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.criticalreview.usuario.model.Usuario;
import com.criticalreview.usuario.repository.UsuarioRepository;

import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@Service
public class UsuarioServiceImpl implements UsuarioService {

    @Autowired
    private UsuarioRepository usuarioRepository;

    @Autowired
    private ReactiveSequenceGeneratorService sequenceGeneratorService;

    @Override
    public Flux<Usuario> obtenerUsuarios() {
        return usuarioRepository.findAll();
    }

    @Override
    public Mono<Usuario> obtenerPorId(int id) {
        return usuarioRepository.findById(id);
    }


    @Override
    public Mono<Usuario> altaUsuario(Usuario usuario) {

        return (usuario.getId() == 0 ? sequenceGeneratorService.generateSequence(Usuario.class.getSimpleName())
                .map(id -> {
                	usuario.setId(id);
                    return usuario;
                }) : Mono.just(usuario))
                .flatMap(usuarioRepository::save);
    }

    @Override
    public Mono<Usuario> actualizarUsuario(int id, Usuario usuario) {
        return obtenerPorId(id).flatMap(u -> {
            u.setNombre(usuario.getNombre());
            u.setApellidos(usuario.getApellidos());
            u.setCorreo(usuario.getCorreo());

            return usuarioRepository.save(u);
        });
    }

    @Override
    public Mono<Void> eliminarUsuario(int id) {
        return obtenerPorId(id).flatMap(usuarioRepository::delete);
    }
}
