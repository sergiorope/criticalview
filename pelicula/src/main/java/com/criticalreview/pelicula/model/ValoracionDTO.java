package com.criticalreview.pelicula.model;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@AllArgsConstructor
@Data
public class ValoracionDTO {
	
    private long id; 	
	private double nota;
	private String fecha;
	private int pelicula_Id;
	private int usuario_Id;

}
