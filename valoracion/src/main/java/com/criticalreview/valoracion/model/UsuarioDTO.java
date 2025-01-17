package com.criticalreview.valoracion.model;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@AllArgsConstructor
@Data
public class UsuarioDTO {
	
	private long id;
	private String nombre;
	private String apellidos;
	private String correo;

}
