package com.example.api_academia.model;

import java.util.ArrayList;
import java.util.List;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Entity
@Getter
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(of = "id")
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @Column(nullable = false)
    private String login;

    @Column
    private String email;

    @Column(nullable = false)
    private String password;

    @Column
    private Boolean active;

    @Column
    private UserRole role;

    @OneToMany(cascade = CascadeType.ALL,orphanRemoval = true, mappedBy = "user")
    private List<Treino> treino = new ArrayList<>();

    public User(String login, String email, String password, UserRole role){
        this.login = login;
        this.email = email;
        this.password = password;
        this.role = role;
        this.active = true;
    }


}
