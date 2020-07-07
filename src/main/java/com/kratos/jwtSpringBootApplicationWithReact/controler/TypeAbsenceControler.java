package com.kratos.jwtSpringBootApplicationWithReact.controler;

import com.kratos.jwtSpringBootApplicationWithReact.models.TypeAbsence;
import com.kratos.jwtSpringBootApplicationWithReact.repositories.TypeAbsenceRepository;
import com.kratos.jwtSpringBootApplicationWithReact.payloads.responses.Response;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.net.URI;
import java.net.URISyntaxException;
import java.util.List;

@RestController
@RequestMapping("/api/typeAbsence")
public class TypeAbsenceControler{
    @Autowired
    TypeAbsenceRepository repo;

    @GetMapping("/all")
    public List<TypeAbsence> findAllTypeAbsence(){
        return repo.findAll();
    }

    @GetMapping("/find/{nom}")
    public ResponseEntity<?> findByName(@PathVariable(value = "nom") String nom){
        TypeAbsence type = repo.findByNom(nom).get();
        if(type == null)
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(new Response(true,"impossible de trouver ce nom"));

        return ResponseEntity.ok(type);
    }

    @PostMapping("/add")
    public ResponseEntity<?> addTypeAbsence(@RequestBody @Valid TypeAbsence typeAbsence) throws URISyntaxException {
        if(repo.existsByNom(typeAbsence.getNom())){
           return ResponseEntity.status(HttpStatus.FOUND).body(new Response(true,"cet typeAbsence exist déja"));
        }
        TypeAbsence response = repo.save(typeAbsence);
        return ResponseEntity.ok(new Response(false,"Enrégistrement réussit..", response, new URI("/api/typeAbsence/"+response.getNom())));
    }

    @PutMapping("/update/{id}")
    public ResponseEntity<?> updateTypeAbsence(@PathVariable(value = "id") Long id,  @Valid @RequestBody TypeAbsence type) throws URISyntaxException {
        if(!repo.existsById(id)){
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(new Response(true,"cet typeAbsence n'exist pas"));
        }
        TypeAbsence typeAbsence = repo. findById(id).orElseThrow(() -> new RuntimeException("impossible de trouver cet id : " + id ));
        typeAbsence.setNom(type.getNom());
        typeAbsence.setNbrJrMax(type.getNbrJrMax());
        repo.save(typeAbsence);
        return ResponseEntity.ok(new Response(false,"Enrégistrement réussit..", typeAbsence, new URI("/api/typeAbsence/"+typeAbsence.getNom())));
    }

    @DeleteMapping("/delete/{nom}")
    public ResponseEntity<?> deleteTypeAbsence(@PathVariable(value = "nom") String nom){
        if(!repo.existsByNom(nom)){
            return new ResponseEntity(new Response(true,"impossible de trouver ce type de congé "), HttpStatus.NOT_FOUND);
        }
        return ResponseEntity.ok("suppression réuissit!");
    }
}
