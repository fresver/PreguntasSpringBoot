package com.questions.dam2.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import com.questions.dam2.entity.Pregunta;

public interface IPreguntaRepository extends JpaRepository<Pregunta, Long>{

}
