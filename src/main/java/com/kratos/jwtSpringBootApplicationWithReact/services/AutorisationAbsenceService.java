package com.kratos.jwtSpringBootApplicationWithReact.services;

import com.kratos.jwtSpringBootApplicationWithReact.models.AutorisationAbsence;
import com.kratos.jwtSpringBootApplicationWithReact.models.Conge;
import com.kratos.jwtSpringBootApplicationWithReact.repositories.AutorisationAbsenceRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class AutorisationAbsenceService {

    @Autowired
    AutorisationAbsenceRepository autorisationRepository;

    // find all Autorisation absence;
    public List<AutorisationAbsence> findAllAutorisationAbsence(){
        return autorisationRepository.findAll();
    }
}
