package com.kratos.jwtSpringBootApplicationWithReact.models;

import javax.persistence.*;
import java.util.Date;
import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name="jour_ferier")
public class JourFerier{
    @Id
    private Date date;

    public JourFerier(Date date) {
        this.date = date;
    }

    public JourFerier() {
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }
}
