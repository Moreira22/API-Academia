package com.example.api_academia.controller;

import org.springframework.web.bind.annotation.RestController;

import com.example.api_academia.Request.RequestTreino;
import com.example.api_academia.model.Treino;
import com.example.api_academia.model.User;
import com.example.api_academia.repository.TreinoRepository;

import jakarta.validation.Valid;
import lombok.AllArgsConstructor;

import java.util.List;
import java.util.ArrayList;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RequestParam;


@RestController
@RequestMapping("/api/treino")
@AllArgsConstructor
public class TreinoController {
    
    @Autowired
    private final TreinoRepository treinoRepository;

    // ============================GET============================
    @GetMapping("/ALL")
    public @ResponseBody List<Treino> getAll() {
        return treinoRepository.findAll();
    }

    @GetMapping
    public @ResponseBody List<Treino> getActive() {
        return treinoRepository.findByActiveTrue();
    }

    @GetMapping("/{id}")
    public ResponseEntity<Treino> getByID(@PathVariable Long id) {
        return treinoRepository.findById(id)
                .map(recordFoumd -> ResponseEntity.ok().body(recordFoumd))
                .orElse(ResponseEntity.notFound().build());
    }

    @GetMapping("/busca/{nome}")
    public @ResponseBody List<Treino> getAll(@PathVariable String nome) {
        return treinoRepository.getByNome(nome);
    }
    
    // =============================================================

    // ============================POST============================
    @PostMapping("/{id}")
    public ResponseEntity<Treino> postTreino(@PathVariable User id,@RequestBody @Valid RequestTreino data) {
        Treino newTreino = new Treino();
        newTreino.setNome(data.nome());
        newTreino.setDescricao(data.descricao());
        newTreino.setUser(id);
        treinoRepository.save(newTreino);
        return ResponseEntity.status(HttpStatus.CREATED).body(newTreino);
    }

    @PostMapping("/ALL/{id}")
    public ResponseEntity<List<Treino>> postALLTreino(@PathVariable User id,@RequestBody @Valid List<RequestTreino> data) {
        List<Treino> newTreinos = new ArrayList<Treino>();
        for (RequestTreino requestTreino : data) {
            Treino newTreino = new Treino(requestTreino);
            newTreino.setUser(id);
            treinoRepository.save(newTreino);
            newTreinos.add(newTreino);
        }
        return ResponseEntity.status(HttpStatus.CREATED).body(newTreinos);

    }
    // =============================================================

    // ============================PUT============================
    @PutMapping("/{id}")
    public ResponseEntity<Treino> putTreino(@PathVariable Long id, @RequestBody RequestTreino treino) {
        return treinoRepository.findById(id)
                .map(recordFoumd -> {
                    recordFoumd.setNome(treino.nome());
                    recordFoumd.setDescricao(treino.descricao());
                    Treino upada = treinoRepository.save(recordFoumd);
                    return ResponseEntity.ok().body(upada);
                })
                .orElse(ResponseEntity.notFound().build());

    }

    @PutMapping()
    public ResponseEntity<Treino> putTreinoBody(@RequestBody RequestTreino treino) {
        return treinoRepository.findById(treino.id())
                .map(recordFoumd -> {
                    recordFoumd.setNome(treino.nome());
                    recordFoumd.setDescricao(treino.descricao());
                    Treino upada = treinoRepository.save(recordFoumd);
                    return ResponseEntity.ok().body(upada);
                })
                .orElse(ResponseEntity.notFound().build());

    }
    // =============================================================

    // ============================DELET============================
    @DeleteMapping("/active/{id}")
    public ResponseEntity<Treino> deleteActive(@PathVariable Long id) {
        return treinoRepository.findById(id)
                .map(recordFoumd -> {
                    recordFoumd.setActive(false);
                    Treino delet = treinoRepository.save(recordFoumd);
                    return ResponseEntity.ok().body(delet);
                })
                .orElse(ResponseEntity.notFound().build());
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Treino> deleteById(@PathVariable Long id) {
        treinoRepository.deleteById(id);
        return ResponseEntity.ok().build();
    }
    // =============================================================

}
