package com.kratos.jwtSpringBootApplicationWithReact.models;

import com.fasterxml.jackson.annotation.JsonFormat;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import java.util.Date;
import java.util.Set;

@Table(name = "calendrier_conge")
@Entity
public class Calendrier {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @Column
    @JsonFormat(pattern = "dd-MM-yyyy", shape = JsonFormat.Shape.STRING)
    private Date dateDebut;

    @Column
    @JsonFormat(pattern = "dd-MM-yyyy", shape = JsonFormat.Shape.STRING)
    private Date dateFin;

    @Column(name = "nombre_jour")
    private int nbrJr;

    public Calendrier(){ nbrJr = 31; }

    public Calendrier(Date deb, Date fin) {
        this.dateDebut = deb;
        this.dateFin = fin;
        nbrJr = 31;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public Date getDateDebut() {
        return dateDebut;
    }

    public void setDateDebut(Date dateDebut) {
        this.dateDebut = dateDebut;
    }

    public Date getDateFin() {
        return dateFin;
    }

    public void setDateFin(Date dateFin) {
        this.dateFin = dateFin;
    }

    public int getNbrJr() {
        return nbrJr;
    }

    public void setNbrJr(int nbrJr) {
        this.nbrJr = nbrJr;
    }
}
