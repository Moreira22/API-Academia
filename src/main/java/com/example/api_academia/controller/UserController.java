package com.example.api_academia.controller;

import org.springframework.web.bind.annotation.RestController;

import com.example.api_academia.Request.RequestRegister;
import com.example.api_academia.Request.RequestUser;
import com.example.api_academia.model.User;
import com.example.api_academia.repository.UserRepository;

import lombok.AllArgsConstructor;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.PathVariable;

@RestController
@RequestMapping("/api/user")
@AllArgsConstructor

public class UserController {
    @Autowired
    private final UserRepository userRepository;

    // ============================GET============================
    @GetMapping("/ALL")
    public @ResponseBody List<User> getAll() {
        return userRepository.findAll();
    }

    @GetMapping()
    public @ResponseBody List<User> getActive() {
        return userRepository.findByActiveTrue();
    }    

    @GetMapping("/{id}")
    public ResponseEntity<User> getById(@PathVariable Long id) {
        return userRepository.findById(id)
                .map(recordFoumd -> ResponseEntity.ok().body(recordFoumd))
                .orElse(ResponseEntity.noContent().build());
    }

    // =============================================================
    // ============================PUT============================
    @PutMapping("/{id}")
    public ResponseEntity<User> putUser(@PathVariable Long id, @RequestBody RequestUser user) {
        return userRepository.findById(id)
                .map(recordFoumd -> {
                    recordFoumd.setLogin(user.login());
                    recordFoumd.setEmail(user.email());
                    User update = userRepository.save(recordFoumd);
                    return ResponseEntity.ok().body(update);
                })
                .orElse(ResponseEntity.notFound().build());
    }

    // =============================================================
    // ============================DELET============================
    @DeleteMapping("/active/{id}")
    public ResponseEntity<User> deleteUser(@PathVariable Long id) {
        return userRepository.findById(id)
                .map(recordFoumd -> {
                    recordFoumd.setActive(false);
                    User delet = userRepository.save(recordFoumd);
                    return ResponseEntity.ok().body(delet);
                })
                .orElse(ResponseEntity.notFound().build());
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<User> delete(@PathVariable Long id) {
        userRepository.deleteById(id);
        return ResponseEntity.ok().build();
    }
    // =============================================================

}
