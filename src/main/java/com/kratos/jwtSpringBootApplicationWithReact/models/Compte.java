package com.kratos.jwtSpringBootApplicationWithReact.models;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;

@Entity
@Table(name="compte")
public class Compte {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int Id;

    @Column
    @NotBlank
    private String email;

    @NotBlank
    @Column
    private String password;

    @Column
    private Boolean isCountLock;

    public Compte(String email, String password) {
        this.email = email;
        this.password = password;
        this.isCountLock = false;
    }

    public Compte() {
        this.isCountLock = false;
    }

    public int getId() {
        return Id;
    }

    public void setId(int id) {
        Id = id;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public Boolean getCountLock() {
        return isCountLock;
    }

    public void setCountLock(Boolean countLock) {
        isCountLock = countLock;
    }
}
