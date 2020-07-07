package com.kratos.jwtSpringBootApplicationWithReact.models;

import com.fasterxml.jackson.annotation.JsonIgnore;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import java.io.Serializable;
import java.util.*;

@Table(name="division")
@Entity
public class Division implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @NotBlank
    @Column
    private String nom;

    @Column
    private int nbrAgents;

    public void Division(){nbrAgents = 0; }
    public void Division(int id, String nom){
        this.id = id;
        this.nom = nom;
        this.nbrAgents = 0;
    }

    public void setId(int id){ this.id = id; }
    public void setNom(String nom ) { this.nom = nom; }
    public void setNbrAgents(int nbrAgents ) {this.nbrAgents = nbrAgents; }

    public int getId() { return this.id; }
    public int getNbrAgents(){ return this.nbrAgents;}
    public String getNom() {return  this.nom ; }

}

