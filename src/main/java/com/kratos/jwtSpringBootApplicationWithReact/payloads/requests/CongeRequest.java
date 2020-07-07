package com.kratos.jwtSpringBootApplicationWithReact.payloads.requests;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.time.Instant;

public class CongeRequest {

    private String DateDeb;


    private String DateFin;

    private String  Commentaires;

    private Long agentId;

    private int typeCongeId;

    public CongeRequest( String dateDeb,  String dateFin, String commentaires,  Long agentId,  int typeCongeId) {
        this.DateDeb = dateDeb;
        this.DateFin = dateFin;
        this.Commentaires = commentaires;
        this.agentId = agentId;
        this.typeCongeId = typeCongeId;
    }

    public CongeRequest() {
    }

    public String getDateDeb() {
        return DateDeb;
    }

    public void setDateDeb(String dateDeb) {
        DateDeb = dateDeb;
    }

    public String getDateFin() {
        return DateFin;
    }

    public void setDateFin(String dateFin) {
        DateFin = dateFin;
    }

    public String getCommentaires() {
        return Commentaires;
    }

    public void setCommentaires(String commentaires) {
        Commentaires = commentaires;
    }

    public Long getAgentId() {
        return agentId;
    }

    public void setAgentId(Long agentId) {
        this.agentId = agentId;
    }

    public int getTypeCongeId() {
        return typeCongeId;
    }

    public void setTypeCongeId(int typeCongeId) {
        this.typeCongeId = typeCongeId;
    }
}
