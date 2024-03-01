package com.example.api_academia.controller;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.api_academia.Request.RequestExercico;
import com.example.api_academia.model.Exercicio;
import com.example.api_academia.model.Treino;
import com.example.api_academia.repository.ExercicioRepository;

import jakarta.validation.Valid;
import lombok.AllArgsConstructor;

@RestController
@RequestMapping("/api/exercicio")
@AllArgsConstructor
public class ExercicioController {

    @Autowired
    private final ExercicioRepository exercicioRepository;

    // ============================POST============================
    @PostMapping("/{id}")
    public  ResponseEntity<Exercicio> postExercicio(@PathVariable Treino id, @RequestBody @Valid RequestExercico data){
        Exercicio newExercicio = new Exercicio();
        newExercicio.setNome(data.nome());
        newExercicio.setDescricao(data.descricao());
        newExercicio.setVez(data.vez());
        newExercicio.setRep(data.rep());
        newExercicio.setPeso(data.peso());
        newExercicio.setTreino(id);
        exercicioRepository.save(newExercicio);
        return ResponseEntity.status(HttpStatus.CREATED).body(newExercicio);
    }

      @PostMapping("/ALL/{id}")
    public ResponseEntity<List<Exercicio>> postALLTreino(@PathVariable Treino id,@RequestBody @Valid List<RequestExercico> data) {
        List<Exercicio> newTreinos = new ArrayList<Exercicio>();
        for (RequestExercico requestExercico : data) {
            Exercicio newExercicio = new Exercicio(requestExercico);
            newExercicio.setTreino(id);
            exercicioRepository.save(newExercicio);
            newTreinos.add(newExercicio);
        }
        return ResponseEntity.status(HttpStatus.CREATED).body(newTreinos);

    }
    // =============================================================

    // ============================PUT============================
    @PutMapping("/{id}")
    public ResponseEntity<Exercicio> putExercicio(@PathVariable Long id, @RequestBody @Valid RequestExercico data) {
        return exercicioRepository.findById(id)
                .map(recordFound -> {
                    recordFound.setNome(data.nome());
                    recordFound.setDescricao(data.descricao());
                    recordFound.setVez(data.vez());
                    recordFound.setRep(data.rep());
                    recordFound.setPeso(data.peso());
                    Exercicio updatd = exercicioRepository.save(recordFound);
                    return ResponseEntity.ok().body(updatd);
                }).orElse(ResponseEntity.noContent().build());
    }
    @PutMapping()
    public ResponseEntity<Exercicio> putExercicioBody(@RequestBody @Valid RequestExercico data) {
        return exercicioRepository.findById(data.id())
                .map(recordFound -> {
                    recordFound.setNome(data.nome());
                    recordFound.setDescricao(data.descricao());
                    recordFound.setVez(data.vez());
                    recordFound.setRep(data.rep());
                    recordFound.setPeso(data.peso());
                    Exercicio updatd = exercicioRepository.save(recordFound);
                    return ResponseEntity.ok().body(updatd);
                }).orElse(ResponseEntity.noContent().build());
    }

    // =============================================================

    // ============================DELET============================
    @DeleteMapping("/{id}")
    public ResponseEntity<Exercicio> deleteById(@PathVariable Long id) {
        exercicioRepository.deleteById(id);
        return ResponseEntity.ok().build();
    }
    // =============================================================
}   
