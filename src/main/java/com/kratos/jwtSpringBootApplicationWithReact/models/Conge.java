package com.kratos.jwtSpringBootApplicationWithReact.models;

import com.fasterxml.jackson.annotation.JsonFormat;

import javax.persistence.*;
import java.io.Serializable;

@Entity
@Table(name="conge")
public class Conge implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name="date_debut")
    @JsonFormat(pattern = "dd-MM-yyyy" , shape = JsonFormat.Shape.STRING)
    private String dateDeb;

    @JsonFormat(pattern = "dd-MM-yyyy" , shape = JsonFormat.Shape.STRING)
    @Column(name="date_fin")
    private String dateFin;

    @Column
    private String commentaires;


    @OneToOne
    @JoinColumn(name="typeconge_id")
    private TypeConge typeConge;

    @OneToOne
    @JoinColumn(name="agent_id")
    private Agent agent;

    public void setCommentaires(String commentaires) {
        this.commentaires = commentaires;
    }

    public TypeConge getTypeConge() {
        return typeConge;
    }

    public void setTypeConge(TypeConge typeConge) {
        this.typeConge = typeConge;
    }

    public Agent getAgent() {
        return agent;
    }

    public void setAgent(Agent agent) {
        this.agent = agent;
    }

    public Conge(String dateDeb, String dateFin,String commentaires) {
        this.dateDeb = dateDeb;
        this.dateFin = dateFin;
        this.commentaires = commentaires;
    }

    public Conge() {
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getDateDeb() {
        return dateDeb;
    }

    public void setDateDeb(String dateDeb) {
        this.dateDeb = dateDeb;
    }

    public String getDateFin() {
        return dateFin;
    }

    public void setDateFin(String dateFin) {
        this.dateFin = dateFin;
    }

    public String getCommentaires(){return this.commentaires; }


}
