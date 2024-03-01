package com.example.api_academia.model;

import java.util.ArrayList;
import java.util.List;

import com.example.api_academia.Request.RequestTreino;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
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
public class Treino {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @Column(name = "nome", length = 200, nullable = false)
    private String nome;

    @Column(length = 200)
    private String descricao;

    @Column
    private Boolean active;

    @OneToMany(cascade = CascadeType.ALL, orphanRemoval = true, mappedBy = "treino")
    private List<Exercicio> exercicio = new ArrayList<>(); 

    public Treino(RequestTreino requestTreino){
        this.nome = requestTreino.nome();
        this.descricao = requestTreino.descricao();
        this.active = true;
    }
}
