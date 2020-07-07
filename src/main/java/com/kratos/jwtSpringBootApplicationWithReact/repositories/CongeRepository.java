package com.kratos.jwtSpringBootApplicationWithReact.repositories;

import com.kratos.jwtSpringBootApplicationWithReact.models.Conge;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface CongeRepository extends JpaRepository<Conge,Long> {

    Optional<Conge> findById(Long id);
    List<Conge> findByAgent(Long agentId);
    Optional<Conge> findByDateFin(String dataFin);
    Optional<Conge> findByDateDeb(String dateDeb);

    Boolean existsByDateDeb(String dateDeb);
    Boolean existsByDateFin(String dateFin);
}
