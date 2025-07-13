package com.questions.dam2.apilol.service;

import java.util.List;
import com.questions.dam2.apilol.entity.Champion;

public interface ILoLService {

	List<Champion> getChampions();
	Champion getRandomChampion(List<Champion> champions);
	boolean verificarNombreCampeon(String nombreIngresado, Champion champion);

}
