package com.questions.dam2.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;
import com.questions.dam2.entity.Pregunta;
import com.questions.dam2.entity.PreguntaSelMult;
import com.questions.dam2.entity.PreguntaSelUnica;
import com.questions.dam2.entity.PreguntaTrueFalse;
import com.questions.dam2.entity.Tematica;
import com.questions.dam2.service.IPreguntaService;

@Controller
public class PreguntaController {

	@Autowired
	private IPreguntaService preguntaService;

	@GetMapping("crear")
	public String crearPregunta() {
		return "vistas/crearpregunta";
	}

	@GetMapping("formSelUnica")
	public String formSelUnica(Model model) {
		model.addAttribute("preguntaSelUnica", new PreguntaSelUnica());
		return "vistas/formselunica";
	}

	@PostMapping("preguntaSelUnica/guardar")
	public String guardarPreguntaSelUnica(@ModelAttribute PreguntaSelUnica preguntaSelUnica) {
		preguntaService.guardarPreguntaSelUnica(preguntaSelUnica);
		return "redirect:/home";
	}

	@GetMapping("formTrueFalse")
	public String formTrueFalse(Model model) {
		model.addAttribute("preguntaTrueFalse", new PreguntaTrueFalse());
		return "vistas/formtruefalse";
	}

	@PostMapping("preguntaTrueFalse/guardar")
	public String guardarPreguntaTrueFalse(@ModelAttribute PreguntaTrueFalse preguntaTrueFalse) {
		preguntaService.guardarPreguntaTrueFalse(preguntaTrueFalse);
		return "redirect:/home";
	}

	@GetMapping("formSelMult")
	public String formSelMult(Model model) {
		model.addAttribute("preguntaSelMult", new PreguntaSelMult());
		return "vistas/formselmult";
	}

	@PostMapping("preguntaSelMult/guardar")
	public String guardarPreguntaSelMult(@ModelAttribute PreguntaSelMult preguntaSelMult) {
		preguntaService.guardarPreguntaSelMult(preguntaSelMult);
		return "redirect:/home";
	}

	@GetMapping("preguntas/ver")
	public String preguntasVer() {
		return "vistas/preguntasver";
	}

	@GetMapping("/preguntas/all")
	public String preguntasAll(@RequestParam(defaultValue = "0") int page, @RequestParam(defaultValue = "5") int size,
			Model model) {

		Page<Pregunta> preguntasPage = preguntaService.preguntasPaginadas(PageRequest.of(page, size));
		model.addAttribute("preguntasPage", preguntasPage);
		model.addAttribute("currentPage", page);
		model.addAttribute("totalPages", preguntasPage.getTotalPages());
		model.addAttribute("pageSize", size);
		model.addAttribute("baseUrl", "/preguntas/all");
		return "vistas/preguntas";
	}

	@GetMapping("/preguntasselunica")
	public String preguntasSelUnica(@RequestParam(defaultValue = "0") int page,
			@RequestParam(defaultValue = "5") int size, Model model) {

		// Obtenemos la página de preguntas con el servicio
		Page<PreguntaSelUnica> preguntasPage = preguntaService.preguntasPaginadasSelUnica(PageRequest.of(page, size));

		// Añadimos los datos al modelo
		model.addAttribute("preguntasPage", preguntasPage);
		model.addAttribute("currentPage", page);
		model.addAttribute("totalPages", preguntasPage.getTotalPages());
		model.addAttribute("pageSize", size);
		model.addAttribute("baseUrl", "/preguntasselunica");

		return "vistas/preguntas";
	}

	@GetMapping("/preguntastruefalse")
	public String preguntasTrueFalse(@RequestParam(defaultValue = "0") int page,
			@RequestParam(defaultValue = "5") int size, Model model) {

		// Obtenemos la página de preguntas con el servicio
		Page<PreguntaTrueFalse> preguntasPage = preguntaService.preguntasPaginadasTrueFalse(PageRequest.of(page, size));

		// Añadimos los datos al modelo
		model.addAttribute("preguntasPage", preguntasPage);
		model.addAttribute("currentPage", page);
		model.addAttribute("totalPages", preguntasPage.getTotalPages());
		model.addAttribute("pageSize", size);
		model.addAttribute("baseUrl", "/preguntastruefalse");

		return "vistas/preguntas";
	}

	@GetMapping("/preguntasselmult")
	public String preguntasSelMult(@RequestParam(defaultValue = "0") int page,
			@RequestParam(defaultValue = "5") int size, Model model) {

		// Obtenemos la página de preguntas con el servicio
		Page<PreguntaSelMult> preguntasPage = preguntaService.preguntasPaginadasSelMult(PageRequest.of(page, size));

		// Añadimos los datos al modelo
		model.addAttribute("preguntasPage", preguntasPage);
		model.addAttribute("currentPage", page);
		model.addAttribute("totalPages", preguntasPage.getTotalPages());
		model.addAttribute("pageSize", size);
		model.addAttribute("baseUrl", "/preguntasselmult");

		return "vistas/preguntas";
	}

	@GetMapping("preguntas/cargarjson")
	public String preguntasCargarJson() {
		return "vistas/preguntascargarjson";
	}

	@PostMapping("preguntas/cargar")
	public String preguntasCargar(@RequestParam MultipartFile file) {
		preguntaService.cargarPreguntasDesdeJson(file);
		return "redirect:/home";
	}

	@GetMapping("preguntas/jugar")
	public String jugarPreguntas() {
		return "vistas/jugar";
	}

	@GetMapping("jugarSelUnica")
	public String jugarSelunica() {
		return "vistas/jugarSelUnica";
	}

	@GetMapping("jugarTrueFalse")
	public String jugarTrueFalse(Model model) {
		List<PreguntaTrueFalse> listaPreguntas = preguntaService.listaPreguntasAleatoriasTrueFalse();
		model.addAttribute("listaPreguntas", listaPreguntas);
		return "vistas/jugarTrueFalse";
	}

	@PostMapping("/validarTrueFalse")
	public String validarTrueFalse(@RequestParam Map<String, String> respuestas, Model model) {
		Map<Long, Boolean> respuestasProcesadas = new HashMap<>();
		for (Map.Entry<String, String> entry : respuestas.entrySet()) {
			Long preguntaId = Long.parseLong(entry.getKey());
			Boolean respuestaUsuario = Boolean.parseBoolean(entry.getValue());
			respuestasProcesadas.put(preguntaId, respuestaUsuario);
		}

		int correctas = preguntaService.validarRespuestasTrueFalse(respuestasProcesadas);

		model.addAttribute("correctas", correctas);
		model.addAttribute("total", respuestasProcesadas.size());

		return "vistas/resultadosTrueFalse";
	}

	@GetMapping("jugarSelMult")
	public String jugarSelMult() {
		return "vistas/jugarSelMult";
	}

	@PostMapping("deletequestion/{id}")
	public String deleteQuestion(@PathVariable Long id) {
		preguntaService.deleteQuestion(id);
		return "redirect:/home";
	}

	@GetMapping("editquestion/{id}")
	public String editQuestion(@PathVariable Long id, Model model) {
		model.addAttribute("pregunta", preguntaService.preguntaPorId(id));
		return "vistas/editquestion";
	}

	@PostMapping("updatequestion")
	public String updateQuestion(@RequestParam Long id, @RequestParam String enunciado,
			@RequestParam(required = false) Boolean respuesta, @RequestParam(required = false) List<String> opciones,
			@RequestParam(required = false) String respuestaCorrecta,
			@RequestParam(required = false) List<String> respuestasCorrectas) {
		preguntaService.updateQuestion(id, enunciado, respuesta, opciones, respuestaCorrecta, respuestasCorrectas);
		return "redirect:/home";
	}

	@GetMapping("preguntasportematica/{tematica}")
	public String preguntasPorTematica(@PathVariable String tematica, Model model) {
		model.addAttribute("preguntasportematica", preguntaService.preguntasPorTematica(tematica));
		return "vistas/preguntasportematica";
	}

}
