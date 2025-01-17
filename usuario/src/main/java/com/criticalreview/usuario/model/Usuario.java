package com.criticalreview.usuario.model;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;



import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@AllArgsConstructor
@Data
@Document(collection = "usuarios")
public class Usuario {

	@Id
    private long id; 	
	private String nombre;
	private String apellidos;
	private String correo;

	
	

}
