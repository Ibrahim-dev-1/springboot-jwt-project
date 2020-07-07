package com.kratos.jwtSpringBootApplicationWithReact.payloads.requests;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;

public class AgentRequest {
    @NotBlank
    private String nom;

    @NotBlank
    private String prenom;

    @NotBlank
    private String dateNais;

    @NotBlank
    private String sexe;

    @NotBlank
    private String cin;

    @NotBlank
    private String tel;

    @NotBlank
    private String sitMatri;

    @NotBlank
    private String fonction;

    @Email
    @NotBlank
    private String email;

    @NotBlank
    private String dateEmbauche;

    private int calendrierId;

    private int statusId;

    private int divisionId;

    public AgentRequest(@NotBlank String nom,
                        @NotBlank String prenom,
                        @NotBlank String dateNais,
                        @NotBlank String sexe,
                        @NotBlank String cin,
                        @NotBlank String tel,
                        @NotBlank String sitMatri,
                        @NotBlank String fonction,
                        @NotBlank String dateEmbauche,
                        @NotBlank @Email String email,
                        int calendrierId,
                        int statusId,
                        int divisionId) {
        this.nom = nom;
        this.prenom = prenom;
        this.dateNais = dateNais;
        this.sexe = sexe;
        this.cin = cin;
        this.tel = tel;
        this.sitMatri = sitMatri;
        this.fonction = fonction;
        this.dateEmbauche = dateEmbauche;
        this.calendrierId = calendrierId;
        this.statusId = statusId;
        this.divisionId = divisionId;
        this.email = email;
    }

    public AgentRequest() {
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

    public void setDataNais(String dateNais) {
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

    public int getCalendrierId() {
        return calendrierId;
    }

    public void setCalendrierId(int calendrierId) {
        this.calendrierId = calendrierId;
    }

    public int getStatusId() {
        return statusId;
    }

    public void setStatusId(int statusId) {
        this.statusId = statusId;
    }

    public int getDivisionId() {
        return divisionId;
    }

    public void setDivisionId(int divisionId) {
        this.divisionId = divisionId;
    }

    public void setDateNais(String dateNais) {
        this.dateNais = dateNais;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }
}
