package com.example.api_academia.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.api_academia.model.Exercicio;

@Repository
public interface ExercicioRepository extends JpaRepository<Exercicio, Long> {

}
