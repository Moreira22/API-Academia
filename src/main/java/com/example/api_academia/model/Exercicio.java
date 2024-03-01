package com.example.api_academia.model;

import com.example.api_academia.Request.RequestExercico;
import com.fasterxml.jackson.annotation.JsonProperty;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Data
@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode(of = "id")
public class Exercicio {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @Column(name = "nome", length = 200, nullable = false)
    private String nome;

    @Column( length = 200)
    private String descricao;

    @Column(nullable = false)
    private  Integer vez;

    @Column(nullable = false)
    private  Integer rep;

    @Column(nullable = false)
    private  Integer peso;

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "treino_id", nullable = false)
    @JsonProperty(access = JsonProperty.Access.WRITE_ONLY)
    private Treino treino;

    public Exercicio(RequestExercico requestExercico){
        this.nome = requestExercico.nome();
        this.descricao = requestExercico.descricao();
        this.vez = requestExercico.vez();
        this.rep = requestExercico.rep();
        this.peso = requestExercico.peso();

    }

}
