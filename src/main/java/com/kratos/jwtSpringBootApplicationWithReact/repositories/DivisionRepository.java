package com.kratos.jwtSpringBootApplicationWithReact.repositories;

import com.kratos.jwtSpringBootApplicationWithReact.models.Division;
import org.springframework.data.domain.Example;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface DivisionRepository extends JpaRepository<Division, Integer> {
    List<Division> findAll();
    Optional<Division> findById(int id);
    Optional<Division> findByNom(String nom);
    Boolean existsByNom(String nom);
    Boolean existsById(int id);
    void deleteById(int id);


}
