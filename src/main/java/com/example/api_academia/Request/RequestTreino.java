package com.example.api_academia.Request;

import java.util.List;

public record RequestTreino(
    Long id,
    String nome,
    String descricao,
    List<RequestExercico> exercicio) {

}
