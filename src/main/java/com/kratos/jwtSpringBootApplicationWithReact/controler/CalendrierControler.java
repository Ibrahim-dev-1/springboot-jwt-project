package com.kratos.jwtSpringBootApplicationWithReact.controler;

import com.kratos.jwtSpringBootApplicationWithReact.models.Calendrier;
import com.kratos.jwtSpringBootApplicationWithReact.payloads.requests.CalendrierRequest;
import com.kratos.jwtSpringBootApplicationWithReact.services.CalendrierService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import javax.validation.Valid;
import java.net.URI;
import java.util.List;

@RestController
@RequestMapping("/api/calendrier")
public class CalendrierControler {
    @Autowired
    CalendrierService service;

    // get all calendrier from database
    @GetMapping("/all")
    public List<Calendrier> getAllCalendrier(){
        return service.getAll();
    }

    // add calendrier path
    @PostMapping("/add")
    public ResponseEntity<?> addCalendrier(@Valid @RequestBody CalendrierRequest cal)
    {
        try{
            Calendrier calendrier = service.addCalendrier(cal);
            URI creation = ServletUriComponentsBuilder
                    .fromCurrentRequestUri().path("/{id}")
                    .buildAndExpand(calendrier.getId()).toUri();

            return ResponseEntity.created(creation).body(calendrier);
        }catch(Exception e){
            return ResponseEntity.ok(e.getMessage());
        }
    }

    // delete calendrier path
    @DeleteMapping("/delete/{id}")
    public ResponseEntity<?> deleteCalendrier(@PathVariable(value = "id") int id)
    {
        try{
            String response = service.deleteCalendrier(id);
            return ResponseEntity.ok(response);
        }catch(Exception e){
            return ResponseEntity.ok(e.getMessage());
        }
    }

    // update calendrier path
    @PutMapping("/update/{id}")
    public ResponseEntity<?> updateCalendrier(@PathVariable(value = "id") int id,@Valid @RequestBody Calendrier cal)
    {
        try{
            String response = service.update(id, cal);
            return ResponseEntity.ok(response);
        }catch(Exception e){
            return ResponseEntity.ok(e.getMessage());
        }
    }
}
