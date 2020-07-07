package com.kratos.jwtSpringBootApplicationWithReact.controler;

import com.kratos.jwtSpringBootApplicationWithReact.exceptions.BadRequest;
import com.kratos.jwtSpringBootApplicationWithReact.exceptions.RessourcesNotFoundException;
import com.kratos.jwtSpringBootApplicationWithReact.models.Agent;
import com.kratos.jwtSpringBootApplicationWithReact.models.Compte;
import com.kratos.jwtSpringBootApplicationWithReact.payloads.requests.LoginRequest;
import com.kratos.jwtSpringBootApplicationWithReact.payloads.responses.ApiResponse;
import com.kratos.jwtSpringBootApplicationWithReact.payloads.responses.JwtResponse;
import com.kratos.jwtSpringBootApplicationWithReact.repositories.AgentRepository;
import com.kratos.jwtSpringBootApplicationWithReact.repositories.CompteRepository;
import com.kratos.jwtSpringBootApplicationWithReact.security.JwtTokenUtils;
import com.kratos.jwtSpringBootApplicationWithReact.security.MyUserDetailService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("api/compte")
public class CompteControler {
    @Autowired
    JwtTokenUtils jwtTokenUtils;

    @Autowired
    MyUserDetailService myUserDetailService;

    @Autowired
    CompteRepository repository;

    @Autowired
    AgentRepository agentRepository;

    // get all compte
    @GetMapping("/all")
    @PreAuthorize("hasRole('ADMIN') and hasRole('GRH')")
    public List<Compte> getAllCompte(){
        return repository.findAll();
    }

    // delete compte
    @DeleteMapping("/delete/{id}")
    public ResponseEntity<?> deleteCompte(@PathVariable("id") int id, @RequestBody  Compte compte) {
        try {
            Compte c = repository.findById(id).get();
            if (c == null)
                throw new RessourcesNotFoundException("compte", "id", null);
            repository.deleteById(id);
            return ResponseEntity.ok(new ApiResponse(true, "Suppression réussit! "));
        } catch (RessourcesNotFoundException e) {
            return ResponseEntity.badRequest().body(e);
        }
    }

    // create compte
        @PostMapping("/add")
        @PreAuthorize("hasRole('ADMIN') and hasRole('GRH')")
        public ResponseEntity<?> addCompte(@Valid @RequestBody  Compte compte) {
            try {
                Agent agent  = agentRepository.findByEmail(compte.getEmail());
                if(agent == null)
                    throw new BadRequest("Email incorrect : Impossible de trouver un compte avec cet à address email . ");

                if (repository.existsByEmail(compte.getEmail()))
                    throw new BadRequest("Ce compte exist déja ");


                Compte comp = new Compte();
                comp.setEmail(compte.getEmail());
                comp.setPassword(compte.getPassword());

                repository.save(comp);

                // update agent
                agent.setCompte(comp);
                agentRepository.save(agent);

                return ResponseEntity.ok(new ApiResponse(true, "Enrégistrement réussit! "));
            } catch (BadRequest | RessourcesNotFoundException e) {
                return ResponseEntity.badRequest().body(e);
            }
        }

    // update Compte
    @PutMapping("/update/{id}")
    public ResponseEntity<?> updatecompte(@PathVariable("id") int id, @RequestBody  Compte compte){
        try{
            Compte c = repository.findById(id).get();
            if(c == null)
                throw new RessourcesNotFoundException("compte","id", null);
            c.setPassword(compte.getPassword());
            c.setEmail(compte.getEmail());
            repository.save(c);
            return ResponseEntity.ok(new ApiResponse(true, "Modification réussit! "));
        }catch (RessourcesNotFoundException e){
            return ResponseEntity.badRequest().body(e);
        }
    }
}
