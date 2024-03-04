package com.example.api_academia.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.api_academia.model.Treino;

@Repository
public interface TreinoRepository extends JpaRepository<Treino, Long>{
    List<Treino> findByActiveTrue();
    List<Treino> getByNome(String nome);
  
}
