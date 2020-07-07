package com.kratos.jwtSpringBootApplicationWithReact.models;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;

@Table(name = "typeAbsence")
@Entity
public class TypeAbsence {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @Column
    private String nom;

    @Column(name = "nombre_jours_maximum")
    private int nbrJrMax;


    public TypeAbsence() {
    }

    public TypeAbsence(String nom, int nbrJrMax) {
        this.nom = nom;
        this.nbrJrMax = nbrJrMax;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getNom() {
        return nom;
    }

    public void setNom(String nom) {
        this.nom = nom;
    }

    public int getNbrJrMax() {
        return nbrJrMax;
    }

    public void setNbrJrMax(int nbrJrMax) {
        this.nbrJrMax = nbrJrMax;
    }



}
