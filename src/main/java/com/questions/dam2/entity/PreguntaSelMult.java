package com.questions.dam2.entity;

import java.util.List;
import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.persistence.CollectionTable;
import jakarta.persistence.Column;
import jakarta.persistence.ElementCollection;
import jakarta.persistence.Entity;
import jakarta.persistence.JoinColumn;

@Entity
@Schema(description = "Entidad que representa una pregunta de selección múltiple")
public class PreguntaSelMult extends Pregunta {

	@ElementCollection
	@CollectionTable(name = "opciones_preguntaSelMult", joinColumns = @JoinColumn(name = "pregunta_id"))
	@Column(name = "opcion")
	@Schema(description = "Lista de opciones posibles a responder", example = "[\"Manuel\", \"Pepe\", \"Manolo\"]")
	private List<String> opciones;

	@ElementCollection
	@CollectionTable(name = "respuestas_correctas_pregunta", joinColumns = @JoinColumn(name = "pregunta_id"))
	@Column(name = "opcion")
	@Schema(description = "Lista de respuestas correctas", example = "[\"Sí\", \"Dos\", \"Hola\"]")
	private List<String> respuestasCorrectas;

	public List<String> getOpciones() {
		return opciones;
	}

	public void setOpciones(List<String> opciones) {
		this.opciones = opciones;
	}

	public List<String> getRespuestasCorrectas() {
		return respuestasCorrectas;
	}

	public void setRespuestasCorrectas(List<String> respuestasCorrectas) {
		this.respuestasCorrectas = respuestasCorrectas;
	}

}
