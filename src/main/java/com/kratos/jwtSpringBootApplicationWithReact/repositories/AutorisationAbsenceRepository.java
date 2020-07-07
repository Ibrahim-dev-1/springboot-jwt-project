package com.kratos.jwtSpringBootApplicationWithReact.repositories;

import com.kratos.jwtSpringBootApplicationWithReact.models.AutorisationAbsence;
import com.kratos.jwtSpringBootApplicationWithReact.models.Conge;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Date;
import java.util.List;
import java.util.Optional;

public interface AutorisationAbsenceRepository extends JpaRepository<AutorisationAbsence, Long> {
    List<AutorisationAbsence> findAll();
    Optional<AutorisationAbsence> findById(Long id);
    List<AutorisationAbsence> findByAgent(Long agentId);
    Optional<AutorisationAbsence> findByDateFin(Date dataFin);
    Optional<AutorisationAbsence> findByDateDeb(Date dateDeb);

    Boolean existsByDateDeb(Date dateDeb);
    Boolean existsByDateFin(Date dateFin);
}
