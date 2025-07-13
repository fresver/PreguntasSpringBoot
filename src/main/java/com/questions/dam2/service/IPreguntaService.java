package com.questions.dam2.service;

import java.util.List;
import java.util.Map;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.web.multipart.MultipartFile;
import com.questions.dam2.entity.Pregunta;
import com.questions.dam2.entity.PreguntaSelMult;
import com.questions.dam2.entity.PreguntaSelUnica;
import com.questions.dam2.entity.PreguntaTrueFalse;

public interface IPreguntaService {
	
	PreguntaSelUnica guardarPreguntaSelUnica(PreguntaSelUnica pregunta);
	PreguntaTrueFalse guardarPreguntaTrueFalse(PreguntaTrueFalse pregunta);
	PreguntaSelMult guardarPreguntaSelMult(PreguntaSelMult preguntaSelMult);
	List<Pregunta> preguntasAll();
	Page<Pregunta> preguntasPaginadas(PageRequest pageable);
	List<PreguntaSelUnica> preguntasSelUnica();
	Page<PreguntaSelUnica> preguntasPaginadasSelUnica(PageRequest pageable);
	List<PreguntaTrueFalse> preguntasTrueFalse();
	Page<PreguntaTrueFalse> preguntasPaginadasTrueFalse(PageRequest pageable);
	List<PreguntaSelMult> preguntasSelMult();
	Page<PreguntaSelMult> preguntasPaginadasSelMult(PageRequest pageable);
	void cargarPreguntasDesdeJson(MultipartFile file);
	List<PreguntaTrueFalse>listaPreguntasAleatoriasTrueFalse();
	int validarRespuestasTrueFalse(Map<Long, Boolean> respuestas);
	void deleteQuestion(Long id);
	Pregunta preguntaPorId(Long id);
	void updateQuestion(Long id, String enunciado, Boolean respuesta, List<String> opciones, String respuestaCorrecta, List<String> respuestasCorrectas);
	Pregunta guardarPregunta(Pregunta pregunta);
	List<Pregunta>preguntasPorTematica(String tematica);
	
}
