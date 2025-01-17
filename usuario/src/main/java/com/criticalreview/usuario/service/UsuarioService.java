package com.criticalreview.usuario.service;

import com.criticalreview.usuario.model.Usuario;

import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

public interface UsuarioService {

	Flux<Usuario> obtenerUsuarios();

	Mono<Usuario> obtenerPorId(int id);

	Mono<Usuario> altaUsuario(Usuario usuario);

	Mono<Usuario> actualizarUsuario(int id, Usuario usuario);

	Mono<Void> eliminarUsuario(int id);

}
