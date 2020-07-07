package com.kratos.jwtSpringBootApplicationWithReact.controler;

import com.kratos.jwtSpringBootApplicationWithReact.exceptions.BadRequest;
import com.kratos.jwtSpringBootApplicationWithReact.exceptions.RessourcesNotFoundException;
import com.kratos.jwtSpringBootApplicationWithReact.models.Conge;
import com.kratos.jwtSpringBootApplicationWithReact.payloads.requests.CongeRequest;
import com.kratos.jwtSpringBootApplicationWithReact.payloads.responses.ApiResponse;
import com.kratos.jwtSpringBootApplicationWithReact.services.CongeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.net.URI;
import java.net.URISyntaxException;
import java.text.ParseException;
import java.util.List;

@RestController
@RequestMapping(value="api/conge")
public class CongeControler {
    @Autowired
    CongeService service;

    // get all conge path
    @GetMapping(value="/all")
    public List<Conge> getAllConge(){
        return service.findAllConge();
    }

    // add conge path
    @PostMapping(value = "/add")
    public ResponseEntity<?> addConge(@Valid @RequestBody  CongeRequest congeRequest) throws URISyntaxException {
        try{
            Conge conge = service.createConge(congeRequest);
            return ResponseEntity
                    .created(new URI(null,null,"/api/conge/"+conge.getId(),"select * from conge where id="+conge.getId() ,null))
                    .body(conge);
        }catch (BadRequest | ParseException bd){
            return ResponseEntity.ok(bd);
        }
    }

    // find One by date Debut and date fin
   @DeleteMapping("/delete/{id}")
    public ResponseEntity<?> deleteConge(@PathVariable(value = "id") Long id){
        try{
            String resultat = service.deleteConge(id);
            return ResponseEntity.ok(new ApiResponse(true,resultat));
        }catch(RessourcesNotFoundException ex){
            return ResponseEntity.badRequest().body(ex);
       }
   }

   // update conge
    @PutMapping("/update/{id}")
    public ResponseEntity<?> updateConge(@PathVariable(value = "id") Long id, @RequestBody CongeRequest congeRequest){
        try{
            String resultat = service.updateConge(id, congeRequest);
            return ResponseEntity.ok(new ApiResponse(true, resultat));
        }catch (RessourcesNotFoundException ec){
            return ResponseEntity.badRequest().body(ec);
        }
    }

}
