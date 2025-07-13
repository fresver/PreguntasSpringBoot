package com.questions.dam2.entity;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.persistence.Entity;

@Entity
@Schema(description = "Entidad que representa una pregunta de verdadero y falso")
public class PreguntaTrueFalse extends Pregunta {

	@Schema(description = "Respuesta correcta", example = "Falso")
	private boolean respuesta;

	public boolean isRespuesta() {
		return respuesta;
	}

	public void setRespuesta(boolean respuesta) {
		this.respuesta = respuesta;
	}

}
