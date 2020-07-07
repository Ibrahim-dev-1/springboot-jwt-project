package com.kratos.jwtSpringBootApplicationWithReact.models;

import javax.persistence.*;

@Entity
@Table(name="absence_hebdomadaire")
public class Absence {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "nombre_personne")
    private int nbrPersonne;

    @Column(name="nombre_absence")
    private int nbrAbsence;

    @OneToOne
    private Semaine semaine;

    public Absence() {
    }

    public Absence(int nbrPersonne, int nbrAbsence, Semaine semaine) {
        this.nbrPersonne = nbrPersonne;
        this.nbrAbsence = nbrAbsence;
        this.semaine = semaine;
    }

    public Long getId() {
        return id;
    }


    public void setId(Long id) {
        this.id = id;
    }

    public int getNbrPersonne() {
        return nbrPersonne;
    }

    public void setNbrPersonne(int nbrPersonne) {
        this.nbrPersonne = nbrPersonne;
    }

    public int getNbrAbsence() {
        return nbrAbsence;
    }

    public void setNbrAbsence(int nbrAbsence) {
        this.nbrAbsence = nbrAbsence;
    }

    public Semaine getSemaine() {
        return semaine;
    }

    public void setSemaine(Semaine semaine) {
        this.semaine = semaine;
    }
}
