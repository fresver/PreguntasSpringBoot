package com.questions.dam2.apilol.controller;

import com.questions.dam2.apilol.entity.Champion;
import com.questions.dam2.apilol.service.ILoLService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import jakarta.servlet.http.HttpSession;

@Controller
public class LoLController {

    @Autowired
    private ILoLService lolService;

    @GetMapping("/champions")
    public String getRandomChampion(Model model, HttpSession session) {
        Champion selectedChampion = lolService.getRandomChampion(lolService.getChampions());
        session.setAttribute("selectedChampion", selectedChampion); 
        model.addAttribute("selectedChampion", selectedChampion); 
        return "vistas/champions";
    }

    @PostMapping("/verificarCampeon")
    public String verificarCampeon(@RequestParam("campeonNombre") String campeonNombre, Model model, HttpSession session) {
        Champion selectedChampion = (Champion) session.getAttribute("selectedChampion");

        if (lolService.verificarNombreCampeon(campeonNombre, selectedChampion)) {
            selectedChampion = lolService.getRandomChampion(lolService.getChampions());
            session.setAttribute("selectedChampion", selectedChampion);
            model.addAttribute("successMessage", "¡Vaya máquina!");
            model.addAttribute("selectedChampion", selectedChampion);
        } else {
            model.addAttribute("errorMessage", "Tontooo");
            model.addAttribute("selectedChampion", selectedChampion);
        }

        return "vistas/champions";
    }
}
