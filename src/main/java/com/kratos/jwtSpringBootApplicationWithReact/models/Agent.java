package com.kratos.jwtSpringBootApplicationWithReact.models;

import com.fasterxml.jackson.annotation.JsonFormat;
import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;

import javax.persistence.*;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import java.io.Serializable;


@Entity
@Table(name = "agent")
public class Agent implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column
    @NotBlank
    private String nom;

    @Column
    @NotBlank
    private String prenom;

    @Column(name = "date_naissance")
    @NotBlank
    private String dateNais;

    @NotBlank
    @Email
    private String email;

    @Column
    @NotBlank
    private String sexe;

    @Column(name = "numero_identite")
    @NotBlank
    private String  cin;

    @Column(name="telephone")
    @NotBlank
    private String tel;

    @Column(name = "situation_matrimoniale")
    @NotBlank
    private String sitMatri;

    @Column
    @NotBlank
    private String fonction;

    @Column(name = "date_embauche")
    @NotBlank
    @JsonFormat(pattern = "dd-MM-yyyy", shape = JsonFormat.Shape.STRING)
    private String dateEmbauche;

    @ManyToOne
    @JoinColumn(name="division_id")
    private Division division;

    @ManyToOne
    @JoinColumn(name="calendrier_id")
    private Calendrier calendrier;

    @ManyToOne
    @JoinColumn(name="status_id")
    @OnDelete(action = OnDeleteAction.CASCADE)
    private Status status;

    @OneToOne
    @JoinColumn(name="compte_id")
    private Compte compte;

    public Agent(@NotBlank String nom,
                 @NotBlank String prenom,
                 @NotBlank String dateNais,
                 @NotBlank String sexe,
                 @NotBlank String cin,
                 @NotBlank String tel,
                 @NotBlank String sitMatri,
                 @NotBlank String fonction,
                 @NotBlank String dateEmbauche,
                 @NotBlank String email,
                 Status status,
                 Division division,
                 Calendrier calendrier){
        this.email = email;
        this.nom = nom;
        this.prenom = prenom;
        this.dateNais = dateNais;
        this.sexe = sexe;
        this.cin = cin;
        this.tel = tel;
        this.sitMatri = sitMatri;
        this.fonction = fonction;
        this.dateEmbauche = dateEmbauche;
        this.status = status;
        this.division = division;
        this.calendrier = calendrier;
    }

    public Agent() {
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getNom() {
        return nom;
    }

    public void setNom(String nom) {
        this.nom = nom;
    }

    public String getPrenom() {
        return prenom;
    }

    public void setPrenom(String prenom) {
        this.prenom = prenom;
    }

    public String getDateNais() {
        return dateNais;
    }

    public void setDateNais(String dateNais) {
        this.dateNais = dateNais;
    }

    public String getSexe() {
        return sexe;
    }

    public void setSexe(String sexe) {
        this.sexe = sexe;
    }

    public String getCin() {
        return cin;
    }

    public void setCin(String cin) {
        this.cin = cin;
    }

    public String getTel() {
        return tel;
    }

    public void setTel(String tel) {
        this.tel = tel;
    }

    public String getSitMatri() {
        return sitMatri;
    }

    public void setSitMatri(String sitMatri) {
        this.sitMatri = sitMatri;
    }

    public String getFonction() {
        return fonction;
    }

    public void setFonction(String fonction) {
        this.fonction = fonction;
    }

    public String getDateEmbauche() {
        return dateEmbauche;
    }

    public void setDateEmbauche(String dateEmbauche) {
        this.dateEmbauche = dateEmbauche;
    }

    public Division getDivision() {
        return division;
    }

    public void setDivision(Division division) {
        this.division = division;
    }

    public Calendrier getCalendrier() {
        return calendrier;
    }

    public void setCalendrier(Calendrier calendrier) {
        this.calendrier = calendrier;
    }

    public Status getStatus() {
        return status;
    }

    public void setStatus(Status status) {
        this.status = status;
    }

    public Compte getCompte() {
        return compte;
    }

    public void setCompte(Compte compte) {
        this.compte = compte;
    }
}
