package com.criticalreview.pelicula.model;

import org.springframework.data.mongodb.core.mapping.Document;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@AllArgsConstructor
@Data
@Document(collection = "peliculas")
public class Pelicula {
	private int id;
	private String nombre;
	private int duracion;
	private String pais;
	private String director;
	private double media;

}
