package com.kratos.jwtSpringBootApplicationWithReact.models;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name = "status")
public class Status {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @NotBlank
    @Column
    private String grade;

    public Status(String grade) {
        this.grade = grade;
    }

    public Status() {
        this.grade = "agent";
    }

    public String getGrade() {
        return grade;
    }

    public void setGrade(String grade) {
        this.grade = grade;
    }
//
//    public Set<Agent> getAgents() {
//        return agents;
//    }
//
//    public void setAgents(Set<Agent> agents) {
//        this.agents = agents;
//    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

}
