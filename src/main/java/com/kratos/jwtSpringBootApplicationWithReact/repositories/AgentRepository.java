package com.kratos.jwtSpringBootApplicationWithReact.repositories;

import com.kratos.jwtSpringBootApplicationWithReact.models.Agent;
import com.kratos.jwtSpringBootApplicationWithReact.models.Compte;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface AgentRepository extends JpaRepository<Agent, Long> {
    Optional<Agent> findById(Long id);
    Optional<Agent> findByNom(String nom);
    Optional<Agent> findByPrenom(String prenom);
    Optional<Agent> findByCin(String cin);
    Optional<Agent> findByTel(String tel);
    Boolean existsByNom(String nom);
    Boolean existsByPrenom(String prenom);
    Boolean existsByTel(String tel);
    Boolean existsByEmail(String email);

    @Query(value = "SELECT count(*) FROM Agent ag WHERE ag.division_id = ?1", nativeQuery = true)
    int countAgentsInDivision(int id);

    Optional<Agent> findByCompte(Compte compte);

    Agent findByEmail(String email);
}
