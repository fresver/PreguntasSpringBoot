package com.questions.dam2.apirest.controller;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import com.questions.dam2.entity.Pregunta;
import com.questions.dam2.entity.PreguntaSelMult;
import com.questions.dam2.entity.PreguntaSelUnica;
import com.questions.dam2.entity.PreguntaTrueFalse;
import com.questions.dam2.service.IPreguntaService;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;

@RestController
@RequestMapping("apirest")
@Tag(name = "Preguntas", description = "Operaciones relacionadas con preguntas")
public class PreguntaRestController {

	@Autowired
	private IPreguntaService preguntaService;

	@GetMapping("preguntas")
	@Operation(summary = "Obtener todas las preguntas", description = "Devuelve una lista de todas las preguntas existentes en la base de datos.", tags = {
			"Preguntas" })
	public List<Pregunta> listaPreguntas() {
		return preguntaService.preguntasAll();
	}

	@GetMapping("pregunta/{id}")
	@Operation(summary = "Obtener una pregunta por su id", description = "Devuelve la pregunta con el id especificado.", tags = {
			"Preguntas" })
	public Pregunta preguntaPorId(@PathVariable Long id) {
		return preguntaService.preguntaPorId(id);
	}

	@PostMapping("deletequestion/{id}")
	@Operation(summary = "Elimina una pregunta por su id", description = "Devuelve una lista de preguntas para comprobar que se eliminó completamente.", tags = {
			"Preguntas" })
	public List<Pregunta> deleteQuestion(@PathVariable Long id) {
		preguntaService.deleteQuestion(id);
		return preguntaService.preguntasAll();
	}

	@PostMapping("crearpreguntaselunica")
	@Operation(summary = "Crear una pregunta de selección única", description = "Crea una nueva pregunta de selección única y la guarda en la base de datos.", tags = {
			"Preguntas" })
	public PreguntaSelUnica crearPreguntaSelUnica(@RequestBody PreguntaSelUnica preguntaSelUnica) {
		return preguntaService.guardarPreguntaSelUnica(preguntaSelUnica);
	}

	@PostMapping("crearpreguntatruefalse")
	@Operation(summary = "Crear una pregunta de verdadero o falso", description = "Crea una nueva pregunta de verdadero o falso y la guarda en la base de datos.", tags = {
			"Preguntas" })
	public PreguntaTrueFalse crearPreguntaTrueFalse(@RequestBody PreguntaTrueFalse preguntaTrueFalse) {
		return preguntaService.guardarPreguntaTrueFalse(preguntaTrueFalse);
	}

	@PostMapping("crearpreguntaselmult")
	@Operation(summary = "Crear una pregunta de selección múltiple", description = "Crea una nueva pregunta de selección múltiple y la guarda en la base de datos.", tags = {
			"Preguntas" })
	public PreguntaSelMult crearPreguntaSelMult(@RequestBody PreguntaSelMult preguntaSelMult) {
		return preguntaService.guardarPreguntaSelMult(preguntaSelMult);
	}

	@PutMapping("actualizarpreguntaselunica")
	@Operation(summary = "Actualizar una pregunta de selección única", description = "Actualiza una nueva pregunta de selección única.", tags = {
			"Preguntas" })
	public PreguntaSelUnica actualizarPreguntaSelUnica(@RequestBody PreguntaSelUnica preguntaSelUnica) {
		return preguntaService.guardarPreguntaSelUnica(preguntaSelUnica);
	}

	@PutMapping("actualizarpreguntatruefalse")
	@Operation(summary = "Actualizar una pregunta de verdadero o falso", description = "Actualiza una nueva pregunta de selección única.", tags = {
			"Preguntas" })
	public PreguntaTrueFalse actualizarPreguntaTrueFalse(@RequestBody PreguntaTrueFalse preguntaTrueFalse) {
		return preguntaService.guardarPreguntaTrueFalse(preguntaTrueFalse);
	}

	@PutMapping("actualizarpreguntaselmult")
	@Operation(summary = "Actualizar una pregunta de selección múltiple", description = "Actualiza una nueva pregunta de selección única.", tags = {
			"Preguntas" })
	public PreguntaSelMult actualizarPreguntaSelMult(@RequestBody PreguntaSelMult preguntaSelMult) {
		return preguntaService.guardarPreguntaSelMult(preguntaSelMult);
	}

}
