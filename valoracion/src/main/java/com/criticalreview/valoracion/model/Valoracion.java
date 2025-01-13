package com.criticalreview.valoracion.model;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;



import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@AllArgsConstructor
@Data
@Document(collection = "valoraciones")
public class Valoracion {

	@Id
    private long id; 	
	private double nota;
	private String fecha;
	private int pelicula_Id;
	private int usuario_Id;
	
	

}
