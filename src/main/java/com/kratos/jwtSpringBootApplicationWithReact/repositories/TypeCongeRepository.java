package com.kratos.jwtSpringBootApplicationWithReact.repositories;

import com.kratos.jwtSpringBootApplicationWithReact.models.TypeConge;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface TypeCongeRepository extends JpaRepository<TypeConge, Integer> {
    Optional<TypeConge> findById(int id);
    Optional<TypeConge> findByNom(String nom);
    boolean existsByNom(String nom);
    boolean existsById(Long id);
    Optional<TypeConge> findById(Long id);
}
