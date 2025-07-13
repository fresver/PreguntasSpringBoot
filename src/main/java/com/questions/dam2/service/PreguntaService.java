package com.questions.dam2.service;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;
import com.fasterxml.jackson.core.exc.StreamReadException;
import com.fasterxml.jackson.databind.DatabindException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.questions.dam2.entity.Pregunta;
import com.questions.dam2.entity.PreguntaDTO;
import com.questions.dam2.entity.PreguntaSelMult;
import com.questions.dam2.entity.PreguntaSelUnica;
import com.questions.dam2.entity.PreguntaTrueFalse;
import com.questions.dam2.repository.IPreguntaRepository;
import com.questions.dam2.repository.IPreguntaSelMultRepository;
import com.questions.dam2.repository.IPreguntaSelUnicaRepository;
import com.questions.dam2.repository.IPreguntaTrueFalseRepository;

@Service
public class PreguntaService implements IPreguntaService {

	@Autowired
	private IPreguntaRepository preguntaRepository;
	@Autowired
	private IPreguntaSelUnicaRepository preguntaSelUnicaRepository;
	@Autowired
	private IPreguntaTrueFalseRepository preguntaTrueFalseRepository;
	@Autowired
	private IPreguntaSelMultRepository preguntaSelMultRepository;

	@Autowired
	private ObjectMapper objectMapper;

	@Override
	public PreguntaSelUnica guardarPreguntaSelUnica(PreguntaSelUnica pregunta) {
		return preguntaRepository.save(pregunta);
	}

	@Override
	public PreguntaTrueFalse guardarPreguntaTrueFalse(PreguntaTrueFalse pregunta) {
		return preguntaRepository.save(pregunta);
	}

	@Override
	public PreguntaSelMult guardarPreguntaSelMult(PreguntaSelMult preguntaSelMult) {
		return preguntaRepository.save(preguntaSelMult);
	}

	// Lista
	@Override
	public List<Pregunta> preguntasAll() {
		return preguntaRepository.findAll();
	}

	// Paginación
	@Override
	public Page<Pregunta> preguntasPaginadas(PageRequest pageable) {
		return preguntaRepository.findAll(pageable);
	}

	@Override
	public List<PreguntaSelUnica> preguntasSelUnica() {
		return preguntaSelUnicaRepository.findAll();
	}

	@Override
	public Page<PreguntaSelUnica> preguntasPaginadasSelUnica(PageRequest pageable) {
		return preguntaSelUnicaRepository.findAll(pageable);
	}

	@Override
	public List<PreguntaTrueFalse> preguntasTrueFalse() {
		return preguntaTrueFalseRepository.findAll();
	}

	@Override
	public Page<PreguntaTrueFalse> preguntasPaginadasTrueFalse(PageRequest pageable) {
		return preguntaTrueFalseRepository.findAll(pageable);
	}

	@Override
	public List<PreguntaSelMult> preguntasSelMult() {
		return preguntaSelMultRepository.findAll();
	}

	@Override
	public Page<PreguntaSelMult> preguntasPaginadasSelMult(PageRequest pageable) {
		return preguntaSelMultRepository.findAll(pageable);
	}

	@Override
	public void cargarPreguntasDesdeJson(MultipartFile file) {
		List<PreguntaDTO> preguntasDto;

		try {
			preguntasDto = Arrays.asList(objectMapper.readValue(file.getInputStream(), PreguntaDTO[].class));

			for (PreguntaDTO preguntaDTO : preguntasDto) {
				Pregunta pregunta = null;

				switch (preguntaDTO.getDtype()) {

				case "selUnica":
					pregunta = new PreguntaSelUnica();
					((PreguntaSelUnica) pregunta).setOpciones(preguntaDTO.getOpciones());
					((PreguntaSelUnica) pregunta).setRespuestaCorrecta(preguntaDTO.getRespuestaCorrecta());
					break;

				case "selMult":
					pregunta = new PreguntaSelMult();
					((PreguntaSelMult) pregunta).setOpciones(preguntaDTO.getOpciones());
					((PreguntaSelMult) pregunta).setRespuestasCorrectas(preguntaDTO.getRespuestasCorrectas());
					break;

				case "trueFalse":
					pregunta = new PreguntaTrueFalse();
					((PreguntaTrueFalse) pregunta).setRespuesta(preguntaDTO.isRespuesta());
					break;

				default:
					throw new IllegalArgumentException("Tipo de pregunta no válido: " + preguntaDTO.getDtype());
				}

				pregunta.setEnunciado(preguntaDTO.getEnunciado());
				pregunta.setTematica(preguntaDTO.getTematica());

				preguntaRepository.save(pregunta);
			}
		} catch (StreamReadException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (DatabindException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

	@Override
	public List<PreguntaTrueFalse> listaPreguntasAleatoriasTrueFalse() {
		List<PreguntaTrueFalse> listaPreguntas = preguntaTrueFalseRepository.findAll();

		Collections.shuffle(listaPreguntas);

		if (listaPreguntas.size() < 10) {
			return listaPreguntas;
		}

		return listaPreguntas.subList(0, 10);
	}

	@Override
	public int validarRespuestasTrueFalse(Map<Long, Boolean> respuestas) {
		int correctas = 0;

		for (Map.Entry<Long, Boolean> entry : respuestas.entrySet()) {
			Long preguntaId = entry.getKey();
			Boolean respuestaUsuario = entry.getValue();

			PreguntaTrueFalse pregunta = (PreguntaTrueFalse) preguntaTrueFalseRepository.findById(preguntaId)
					.orElseThrow(() -> new IllegalArgumentException("Pregunta no encontrada con ID: " + preguntaId));

			if (pregunta.isRespuesta() == (respuestaUsuario)) {
				correctas++;
			}
		}

		return correctas;
	}

	@Override
	public void deleteQuestion(Long id) {
		preguntaRepository.deleteById(id);
	}

	@Override
	public Pregunta preguntaPorId(Long id) {
		return preguntaRepository.findById(id).get();
	}

	@Override
	public void updateQuestion(Long id, String enunciado, Boolean respuesta, List<String> opciones,
			String respuestaCorrecta, List<String> respuestasCorrectas) {

		Pregunta pregunta = preguntaRepository.findById(id)
				.orElseThrow(() -> new IllegalArgumentException("Pregunta no encontrada con ID: " + id));

		pregunta.setEnunciado(enunciado);

		if (pregunta instanceof PreguntaTrueFalse) {
			if (respuesta != null) {
				((PreguntaTrueFalse) pregunta).setRespuesta(respuesta);
			}
		} else if (pregunta instanceof PreguntaSelUnica) {
			if (opciones != null && !opciones.isEmpty()) {
				((PreguntaSelUnica) pregunta).setOpciones(opciones);
			}
			if (respuestaCorrecta != null && !respuestaCorrecta.isEmpty()) {
				((PreguntaSelUnica) pregunta).setRespuestaCorrecta(respuestaCorrecta);
			}
		} else if (pregunta instanceof PreguntaSelMult) {
			if (opciones != null && !opciones.isEmpty()) {
				((PreguntaSelMult) pregunta).setOpciones(opciones);
			}
			if (respuestasCorrectas != null && !respuestasCorrectas.isEmpty()) {
				((PreguntaSelMult) pregunta).setRespuestasCorrectas(respuestasCorrectas);
			}
		}

		preguntaRepository.save(pregunta);
	}

	@Override
	public Pregunta guardarPregunta(Pregunta pregunta) {
		return preguntaRepository.save(pregunta);
	}

	@Override
	public List<Pregunta> preguntasPorTematica(String tematica) {
		return preguntaRepository.findAll().stream()
				.filter(s -> s.getTematica().getTematica().toUpperCase().equals(tematica.toUpperCase())).toList();
	}

}
