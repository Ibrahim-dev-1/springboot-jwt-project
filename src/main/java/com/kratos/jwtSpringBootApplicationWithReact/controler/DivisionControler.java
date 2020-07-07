package com.kratos.jwtSpringBootApplicationWithReact.controler;

import com.kratos.jwtSpringBootApplicationWithReact.models.Division;
import com.kratos.jwtSpringBootApplicationWithReact.repositories.DivisionRepository;
import com.kratos.jwtSpringBootApplicationWithReact.payloads.responses.Response;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.net.URI;
import java.util.List;

@RestController
@RequestMapping("api/division")
public class DivisionControler {
    @Autowired
    private DivisionRepository repo;

    @GetMapping(value = "/all")
    @PreAuthorize("hasRole('ADMIN') and hasRole('GRH')")
    public List<Division> findAllDivision(){
        return repo.findAll();
    }

    @PostMapping("/add")
    @PreAuthorize("hasRole('ADMIN') and hasRole('GRH')")
    public ResponseEntity<?> addDivsion(@Valid @RequestBody Division division) {
        try {
            if (repo.existsByNom(division.getNom()))
                throw new Exception("cette division exist déja");
            return new ResponseEntity(new Response(false,"enrégistrement réuissit",repo.save(division), new URI("api/division/"+division.getId())), HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity(e.getMessage()+" cause: " +e.getCause(),HttpStatus.BAD_REQUEST );
        }
    }
    @GetMapping("/{id}")
    public ResponseEntity<?> findOnDivision(@PathVariable(value="id") int id){
        try{
            Division division = repo.findById(id).orElseThrow(() -> new Exception("Impossible de trouver cette division "));
            return ResponseEntity.ok(division);
        }catch (Exception e){
            return ResponseEntity.badRequest().body(e.getMessage());
        }

    }

    @PutMapping("/update/{id}")
    public ResponseEntity<?> updateDivision(@PathVariable(value = "id") int id, @Valid @RequestBody Division division){
        try {
            Division div = repo.findById(id).orElseThrow(() -> new RuntimeException("Erreur de fetch de id division "));
            div.setNom(division.getNom());
            repo.save(div);
            return ResponseEntity.ok("Modification réussi !");
        }catch (Exception e){
            return new ResponseEntity(e.getMessage(), HttpStatus.BAD_REQUEST);
        }
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<?> deleteDivision(@PathVariable(value="id") int id){
        try{
             if(!repo.existsById(id))
                 throw new Exception("Cette division n'exist pas ");
             repo.deleteById(id);
             return ResponseEntity.ok("Suppression réussi !");
        }catch(Exception e) {
            return new ResponseEntity(e.getMessage(),HttpStatus.BAD_REQUEST);
        }
    }
}
