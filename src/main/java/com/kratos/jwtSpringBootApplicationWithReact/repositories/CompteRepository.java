package com.kratos.jwtSpringBootApplicationWithReact.repositories;

import com.kratos.jwtSpringBootApplicationWithReact.models.Compte;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface CompteRepository extends JpaRepository<Compte, Integer> {
   Optional<Compte> findByEmail(String email);
   Boolean existsByEmail(String email);
}
