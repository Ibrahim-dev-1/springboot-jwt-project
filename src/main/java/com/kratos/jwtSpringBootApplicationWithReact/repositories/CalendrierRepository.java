package com.kratos.jwtSpringBootApplicationWithReact.repositories;

import com.kratos.jwtSpringBootApplicationWithReact.models.Calendrier;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.Date;
import java.util.List;
import java.util.Optional;

@Repository
public interface CalendrierRepository extends JpaRepository<Calendrier, Integer> {
    Optional< Calendrier> findById(int id);
    Boolean existsById(int id);
    Boolean existsByDateDebut(String dateDeb);
    Boolean existsByDateFin(String dateFin);

    @Query("SELECT c from Calendrier c WHERE c.dateDebut= :dateDebut and c.dateFin= :dateFin")
    List<Calendrier> findDateDebutAndDateFin(@Param("dateDebut") Date dateDebut , @Param("dateFin") Date dateFin);

    boolean existsByDateDebutAndDateFin(Date deb, Date fin);
}
