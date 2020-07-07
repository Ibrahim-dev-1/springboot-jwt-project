package com.kratos.jwtSpringBootApplicationWithReact.controler;

import com.kratos.jwtSpringBootApplicationWithReact.models.Agent;
import com.kratos.jwtSpringBootApplicationWithReact.payloads.requests.AgentRequest;
import com.kratos.jwtSpringBootApplicationWithReact.payloads.responses.Response;
import com.kratos.jwtSpringBootApplicationWithReact.services.AgentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import javax.validation.Valid;
import java.net.URI;
import java.net.URISyntaxException;
import java.util.List;

@RestController
@RequestMapping("/api/agent")
public class AgentControler{
    @Autowired
    AgentService service;

    @GetMapping("/all")
    public List<Agent> getAll(){
        return service.getAll();
    }

    @PostMapping("/add")
    public ResponseEntity<?> addAgent(@Valid @RequestBody AgentRequest agent) throws URISyntaxException {
        try{
            Agent ag = service.addAgent(agent);
            URI creation = ServletUriComponentsBuilder
                    .fromCurrentRequest().path("/{Id}")
                    .buildAndExpand(ag.getId()).toUri();

            return ResponseEntity.created(creation).body(ag);
        }catch (Exception e){
            URI path = new URI("/api/agent/add");
            return ResponseEntity.badRequest().body(new Response(true, e.getMessage(),null, path));
        }
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<?> deleteAgent(@PathVariable(value = "id") Long id) throws URISyntaxException {
        try{
            service.delete(id);
            return ResponseEntity.ok(new Response(false,"deleteSuccess", null, new URI("api/agent/all")));
        }catch (Exception e){
            URI path = new URI("/api/agent/delete/" + id);
            return ResponseEntity.badRequest().body(new Response(true, e.getMessage(),null,path ));
        }
    }

    @PutMapping("/update/{id}")
    public ResponseEntity<?> updateAgent(@PathVariable(value = "id") long id, @Valid @RequestBody AgentRequest agentRequest) throws URISyntaxException {
        try {
            String response = service.update(id, agentRequest);

            return ResponseEntity.ok(response);
        }catch (Exception e){
            URI path = new URI("/api/agent/delete/" + id);
            return ResponseEntity.badRequest().body(new Response(true, e.getMessage(),null,path ));
        }
    }

}
