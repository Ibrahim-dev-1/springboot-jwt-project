package com.kratos.jwtSpringBootApplicationWithReact.models;

import javax.persistence.*;
import java.util.Date;
import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name="semaine")
public class Semaine {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name="date_debut")
    private Date dateDeb;

    @Column(name="date_fin")
    private Date dateFin;

    @OneToMany(mappedBy = "semaine", fetch = FetchType.LAZY)
    private Set<Absence> absences = new HashSet<>();

    public Semaine(Date dateDeb, Date dateFin, Set<Absence> absences) {
        this.dateDeb = dateDeb;
        this.dateFin = dateFin;
        this.absences = absences;
    }

    public Semaine() {
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Date getDateDeb() {
        return dateDeb;
    }

    public void setDateDeb(Date dateDeb) {
        this.dateDeb = dateDeb;
    }

    public Date getDateFin() {
        return dateFin;
    }

    public void setDateFin(Date dateFin) {
        this.dateFin = dateFin;
    }

    public Set<Absence> getAbsences() {
        return absences;
    }

    public void setAbsences(Set<Absence> absences) {
        this.absences = absences;
    }
}
