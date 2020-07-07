package com.kratos.jwtSpringBootApplicationWithReact.controler;

import com.kratos.jwtSpringBootApplicationWithReact.models.AutorisationAbsence;
import com.kratos.jwtSpringBootApplicationWithReact.services.AutorisationAbsenceService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api/autorisationAbsence")
public class AutorisationAbsenceControler {

    @Autowired
    AutorisationAbsenceService service;

    // get all conge path
    @GetMapping(value="/all")
    public List<AutorisationAbsence> getAllConge(){
        return service.findAllAutorisationAbsence();
    }
}
