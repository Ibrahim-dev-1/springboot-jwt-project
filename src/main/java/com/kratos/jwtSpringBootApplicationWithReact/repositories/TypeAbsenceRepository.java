package com.kratos.jwtSpringBootApplicationWithReact.repositories;

import com.kratos.jwtSpringBootApplicationWithReact.models.TypeAbsence;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface TypeAbsenceRepository extends JpaRepository<TypeAbsence,Integer > {
    Optional<TypeAbsence> findById(int id);
    Optional<TypeAbsence> findByNom(String nom);
    boolean existsByNom(String nom);
    Optional<TypeAbsence> findById(Long id);
    boolean existsById(Long id);
}
