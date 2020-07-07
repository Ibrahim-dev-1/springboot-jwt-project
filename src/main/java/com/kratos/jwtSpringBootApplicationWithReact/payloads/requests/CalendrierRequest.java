package com.kratos.jwtSpringBootApplicationWithReact.payloads.requests;

public class CalendrierRequest {
    private String dateDebut;
    private String dateFin;


    public CalendrierRequest() {
    }

    public CalendrierRequest(String dateDebut, String dateFin) {
        this.dateDebut = dateDebut;
        this.dateFin = dateFin;
    }

    public String getDateDebut() {
        return dateDebut;
    }

    public void setDateDebut(String dateDebut) {
        this.dateDebut = dateDebut;
    }

    public String getDateFin() {
        return dateFin;
    }

    public void setDateFin(String dateFin) {
        this.dateFin = dateFin;
    }

//    public int getNbrJr() {
//        return nbrJr;
//    }
//
//    public void setNbrJr(int nbrJr) {
//        this.nbrJr = nbrJr;
//    }
}
