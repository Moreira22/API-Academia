package com.example.api_academia.Request;

import com.example.api_academia.model.Treino;

public record RequestExercico(
    Long id,
    String nome,
    String descricao,
    Integer vez,
    Integer rep,
    Integer peso,
    Treino treino) {

}
