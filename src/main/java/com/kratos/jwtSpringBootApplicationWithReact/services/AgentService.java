package com.kratos.jwtSpringBootApplicationWithReact.services;

import com.kratos.jwtSpringBootApplicationWithReact.models.Agent;
import com.kratos.jwtSpringBootApplicationWithReact.models.Calendrier;
import com.kratos.jwtSpringBootApplicationWithReact.models.Division;
import com.kratos.jwtSpringBootApplicationWithReact.models.Status;
import com.kratos.jwtSpringBootApplicationWithReact.payloads.requests.AgentRequest;
import com.kratos.jwtSpringBootApplicationWithReact.repositories.AgentRepository;
import com.kratos.jwtSpringBootApplicationWithReact.repositories.CalendrierRepository;
import com.kratos.jwtSpringBootApplicationWithReact.repositories.DivisionRepository;
import com.kratos.jwtSpringBootApplicationWithReact.repositories.StatusRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class AgentService {
    @Autowired
    AgentRepository agentRepository;

    @Autowired
    StatusRepository statusRepository;

    @Autowired
    DivisionRepository divisionRepository;

    @Autowired
    CalendrierRepository calendrierRepository;

    // Add agent fonction
    public Agent addAgent(AgentRequest ag) throws Exception{
        if (agentRepository.existsByNom(ag.getNom()) || agentRepository.existsByPrenom(ag.getPrenom()) || agentRepository.existsByTel(ag.getTel())) {
            throw new Exception("Cet Agent exist déjà !");
        }
        Status status = statusRepository.findById(ag.getStatusId()).orElseThrow(() -> new Exception("Cet status n'exist pas! veuillez le créér avant de continué l'opération!"));
        Calendrier calendrier =  calendrierRepository.findById(ag.getCalendrierId()).orElseThrow(() -> new Exception("Ce calendrier n'exist pas! veuillez le créér avant de continué !"));
        Division division = divisionRepository.findById(ag.getDivisionId()).orElseThrow(() -> new Exception("cette division n'exist pas! veuille la créée avant de continué l'opération !"));
        Agent agent = new Agent(
                ag.getNom(),
                ag.getPrenom(),
                ag.getDateNais(),
                ag.getSexe(),
                ag.getCin(),
                ag.getTel(),
                ag.getSitMatri(),
                ag.getFonction(),
                ag.getDateEmbauche(),
                ag.getEmail(),
                status,
                division,
                calendrier
        );
        //update division
        division.setNbrAgents(division.getNbrAgents() + 1);
        divisionRepository.save(division);


        return agentRepository.save(agent);
    }

    // get all agents from database
    public List<Agent> getAll() {

        return agentRepository.findAll();
    }

    // delete agent
    public void delete(Long id) throws Exception {
        if(!agentRepository.existsById(id))
            throw new Exception("impossible de trouver cet agent d'ID: " + id);

        agentRepository.deleteById(id);
    }

    // delete agent
    public String delete(long id) throws Exception{
        if(!agentRepository.existsById(id))
            throw new Exception("Cet agent n'exist pas! ");

        agentRepository.deleteById(id);
        return "Suppression réussit";
    }

    // update agent
    public String update(long id, AgentRequest ag) throws Exception{
        Agent agent = agentRepository.findById(id).orElseThrow(() -> new Exception("cet agent n'exist pas! "));
        if (agentRepository.existsByNom(ag.getNom()) || agentRepository.existsByPrenom(ag.getPrenom()) || agentRepository.existsByTel(ag.getTel())) {
            throw new Exception("Cet Agent exist déjà !");
        }

        Status status = statusRepository.findById(ag.getStatusId()).orElseThrow(() -> new Exception("Cet status n'exist pas! veuillez le créér avant de continué l'opération!"));
        Calendrier calendrier =  calendrierRepository.findById(ag.getCalendrierId()).orElseThrow(() -> new Exception("Ce calendrier n'exist pas! veuillez le créér avant de continué !"));
        Division division = divisionRepository.findById(ag.getDivisionId()).orElseThrow(() -> new Exception("cette division n'exist pas! veuille la créée avant de continué l'opération !"));

        agent.setNom(ag.getNom());
        agent.setPrenom(ag.getPrenom());
        agent.setDateNais(ag.getDateNais());
        agent.setSexe(ag.getSexe());
        agent.setCin(ag.getCin());
        agent.setTel(ag.getTel());
        agent.setSitMatri(ag.getSitMatri());
        agent.setFonction(ag.getFonction());
        agent.setDateEmbauche(ag.getDateEmbauche());
        agent.setStatus(status);
        agent.setDivision(division);
        agent.setCalendrier(calendrier);

        agentRepository.save(agent);
        return "modification réussit ! ";
    }

}
