package com.criticalreview.valoracion.model;



import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@AllArgsConstructor
@Data
public class PeliculaDTO {
	
	private long id;
	private String nombre;
	private int duracion;
	private String pais;
	private String director;
	private double media;

}
