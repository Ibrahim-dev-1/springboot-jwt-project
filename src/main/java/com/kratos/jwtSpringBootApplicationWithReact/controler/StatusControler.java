package com.kratos.jwtSpringBootApplicationWithReact.controler;

import com.kratos.jwtSpringBootApplicationWithReact.models.Status;
import com.kratos.jwtSpringBootApplicationWithReact.repositories.StatusRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/api/status")
public class StatusControler {
    @Autowired
    private StatusRepository repo;

    private Logger logger = LoggerFactory.getLogger(StatusControler.class);

    @GetMapping("/init")
    public String init(){
        if(!repo.existsByGrade("agent")){
            logger.debug("création du status agent");
            repo.save(new Status("agent"));
        }
        if(!repo.existsByGrade("chef_service")){
            logger.debug("création du status chef_service");
            repo.save(new Status("chef_service"));
        }
        if(!repo.existsByGrade("directeur")){
            logger.debug("création du status directeur");
            logger.info("creation du status");
            repo.save(new Status("directeur"));
        }
        return "Status initialiser";
    }

    @GetMapping("/all")
    public List<Status> getAllStatus(){
        return repo.findAll();
    }

    @PostMapping("/add")
    public ResponseEntity<?> addStatus(@Valid @RequestBody Status status ){
        try{
            if(repo.existsByGrade(status.getGrade()))
                throw new Exception("ce nom exist déja");

            return ResponseEntity.ok(repo.save(status));
        }catch (Exception e){
            return ResponseEntity.ok(e.getMessage() + " trace : " + e.getStackTrace());
        }
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<?> addStatus(@PathVariable(value = "id") int id ){
        try{
            if(!repo.existsById(id))
                throw new Exception("ce Status n'exist pas!");

            repo.deleteById(id);
            return ResponseEntity.ok("suppression réussit!");
        }catch (Exception e){
            return ResponseEntity.ok(e.getMessage() + " trace : " + e.getStackTrace());
        }
    }

    // Update Status
    @PutMapping("/update/{id}")
    public ResponseEntity<?> updateStatus(@PathVariable(value = "id") int id, @RequestBody @Valid Status  s){
        try{
            Status status = repo.findById(id).orElseThrow(() -> new Exception("ce status n'exist pas ! "));
            status.setGrade(s.getGrade());
            repo.save(status);
            return ResponseEntity.ok("suppression réussit!");
        }catch (Exception e){
            return ResponseEntity.ok(e.getMessage() + " trace : " + e.getStackTrace());
        }
    }
}
