package com.kratos.jwtSpringBootApplicationWithReact.controler;

import com.kratos.jwtSpringBootApplicationWithReact.models.TypeConge;
import com.kratos.jwtSpringBootApplicationWithReact.repositories.TypeCongeRepository;
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
@RequestMapping("/api/typeConge")
public class TypeCongeControler{
    @Autowired
    TypeCongeRepository repo;

    @GetMapping("/all")
    public List<TypeConge> findAllTypeConge(){
        return repo.findAll();
    }

    @GetMapping("/find/{nom}")
    public ResponseEntity<?> findByName(@PathVariable(value = "nom") String nom){
        TypeConge type = repo.findByNom(nom).get();
        if(type == null)
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(new Response(true,"impossible de trouver ce type de Conge"));

        return ResponseEntity.ok(type);
    }

    @PostMapping("/add")
    public ResponseEntity<?> addTypeConge(@RequestBody @Valid TypeConge typeConge) throws URISyntaxException{
        if(repo.existsByNom(typeConge.getNom())){
            return ResponseEntity.status(HttpStatus.FOUND).body(new Response(true,"cet type de Congé exist déja"));
        }
        TypeConge response = repo.save(typeConge);
        return ResponseEntity.ok(new Response(false,"Enrégistrement réussit..", response, new URI("/api/agent/"+response.getNom())));
    }

    @PutMapping("/update/{nom}")
    public ResponseEntity<?> updateTypeConge(@PathVariable(value = "nom") String nom,  @Valid @RequestBody TypeConge type) throws URISyntaxException {
        TypeConge typeConge = repo.findByNom(nom).get();
        if(typeConge == null)
            return new ResponseEntity(new Response(true, "ce type de congé n'exist pas "), HttpStatus.NOT_FOUND);

        typeConge.setNom(type.getNom());
        typeConge.setNbrJrMax(type.getNbrJrMax());
        repo.save(typeConge);
        return ResponseEntity.ok(new Response(false,"Mise à jour réussit!", typeConge, new URI("/api/agent/"+typeConge.getNom())));
    }

    @DeleteMapping("/delete/{nom}")
    public ResponseEntity<?> deleteTypeConge(@PathVariable(value = "nom") String nom){
        if(!repo.existsByNom(nom)){
            return new ResponseEntity(new Response(true, "Impossible de trouver ce type de congé "), HttpStatus.NOT_FOUND);
        }
        return ResponseEntity.ok(new Response("Suppression réuissit !", repo.findAll()));
    }
}
