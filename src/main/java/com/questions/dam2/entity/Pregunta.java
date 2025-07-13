package com.questions.dam2.entity;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table(name = "pregunta")
@Schema(description = "Entidad que representa una pregunta")
public abstract class Pregunta {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Schema(description = "Identificador único de la entidad", example = "1")
	private Long id;

	@Schema(description = "Enunciado de la pregunta", example = "¿Cuánto es 2+2?")
	private String enunciado;

	@Schema(description = "Temática de la pregunta", example = "PROGRAMACIÓN")
	private Tematica tematica;

	public Pregunta() {
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getEnunciado() {
		return enunciado;
	}

	public void setEnunciado(String enunciado) {
		this.enunciado = enunciado;
	}

	public Tematica getTematica() {
		return tematica;
	}

	public void setTematica(Tematica tematica) {
		this.tematica = tematica;
	}

}
