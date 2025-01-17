package com.criticalreview.usuario.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.criticalreview.usuario.model.Usuario;
import com.criticalreview.usuario.service.UsuarioService;

import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@RestController
public class UsuarioController {

	@Autowired
	UsuarioService usuarioService;


    @GetMapping(value = "/usuarios/list")
	public ResponseEntity<Flux<Usuario>> obtenerUsuarios() {

		return new ResponseEntity<>(usuarioService.obtenerUsuarios(), HttpStatus.OK);
	}


	@GetMapping(value = "usuarios/{id}")
	public Mono<ResponseEntity<Usuario>> obtenerUsuarioPorId(@PathVariable("id") int id) {

		return usuarioService.obtenerPorId(id)
				.map(p -> ResponseEntity.ok(p))
				.defaultIfEmpty(ResponseEntity.notFound().build());
	}


	@PostMapping(value = "usuarios/alta", consumes = MediaType.APPLICATION_JSON_VALUE)
	public Mono<ResponseEntity<Usuario>> altaUsuario(@RequestBody Usuario usuario) {

		return usuarioService.altaUsuario(usuario)
				.map(p -> ResponseEntity.ok(p));

	}

	@PutMapping(value = "usuarios/actualizar/{id}")
	public Mono<ResponseEntity<Usuario>> actualizarUsuario(@PathVariable("id") int id, @RequestBody Usuario usuario) {

		return usuarioService.actualizarUsuario(id, usuario)
				.map(p -> ResponseEntity.ok(p))
				.defaultIfEmpty(ResponseEntity.notFound().build());

	}

	@DeleteMapping(value = "usuarios/eliminar/{id}")
	public Mono<ResponseEntity<Void>> borrarUsuario(@PathVariable("id") int id) {

		return usuarioService.eliminarUsuario(id)
				.map(p -> ResponseEntity.ok(p))
				.defaultIfEmpty(ResponseEntity.notFound().build());

	}

}
