package com.questions.dam2.entity;

import java.util.List;
import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.persistence.CollectionTable;
import jakarta.persistence.Column;
import jakarta.persistence.ElementCollection;
import jakarta.persistence.Entity;
import jakarta.persistence.JoinColumn;

@Entity
@Schema(description = "Entidad que representa una pregunta de selección única")
public class PreguntaSelUnica extends Pregunta {

	@ElementCollection
	@CollectionTable(name = "opciones_preguntaSelUnica", joinColumns = @JoinColumn(name = "pregunta_id"))
	@Column(name = "opcion")
	@Schema(description = "Lista de opciones posibles a responder", example = "[\"París\", \"Londres\", \"Roma\"]")
	private List<String> opciones;

	@Schema(description = "Respuesta correcta", example = "Luis")
	private String respuestaCorrecta;

	public List<String> getOpciones() {
		return opciones;
	}

	public void setOpciones(List<String> opciones) {
		this.opciones = opciones;
	}

	public String getRespuestaCorrecta() {
		return respuestaCorrecta;
	}

	public void setRespuestaCorrecta(String respuestaCorrecta) {
		this.respuestaCorrecta = respuestaCorrecta;
	}

}
