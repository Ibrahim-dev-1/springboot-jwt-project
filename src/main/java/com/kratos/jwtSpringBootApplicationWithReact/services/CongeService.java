package com.kratos.jwtSpringBootApplicationWithReact.services;

import com.kratos.jwtSpringBootApplicationWithReact.exceptions.BadRequest;
import com.kratos.jwtSpringBootApplicationWithReact.exceptions.RessourcesNotFoundException;
import com.kratos.jwtSpringBootApplicationWithReact.models.*;
import com.kratos.jwtSpringBootApplicationWithReact.payloads.requests.CongeRequest;
import com.kratos.jwtSpringBootApplicationWithReact.repositories.AgentRepository;
import com.kratos.jwtSpringBootApplicationWithReact.repositories.CongeRepository;
import com.kratos.jwtSpringBootApplicationWithReact.repositories.TypeCongeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

@Service
public class
CongeService {
    @Autowired
    CongeRepository congeRepository;

    @Autowired
    AgentRepository agentRepository;

    @Autowired
    TypeCongeRepository typeCongeRepository;

   // add Conge;
    public Conge createConge(CongeRequest congeRequest) throws BadRequest, RessourcesNotFoundException, ParseException {

        if(congeRepository.existsByDateDeb(congeRequest.getDateDeb()) && congeRepository.existsByDateFin(congeRequest.getDateFin())){
            throw new BadRequest("cet congé exists déjà ! ");
        }
        // vérification des dates des demandes de congé
        Date now = new Date();
        Calendar cal = Calendar.getInstance();
        cal.setTime(now);
        int nowYear = cal.get(Calendar.YEAR);


        SimpleDateFormat formatter = new SimpleDateFormat("dd-MM-yyyy");
        Date deb = formatter.parse(congeRequest.getDateDeb());
        Date fin = formatter.parse(congeRequest.getDateFin());
        cal.setTime(deb);
        int debYear = cal.get(Calendar.YEAR);

        cal.setTime(fin);
        int finYear = cal.get(Calendar.YEAR);

        if(deb.after(fin)){
            throw new BadRequest("La date debut ne doit pas venir après la date fin!");
        }

        if(nowYear != debYear || nowYear != finYear ){
            throw new BadRequest("Vous devez faire la demande dans l'année courrante");
        }

        /*
        * Vérification
        * si un agent éssaie de créé un congé, le system vérifie voir s'il est dans le délai du calendrier?
        * s 'il n'est pas dans le délai du calendrier alors le system lui renvoi une erreur
        * sinon le system enrégistre le congé demander
        * */


        // recherche
        Agent agent  = agentRepository.findById(congeRequest.getAgentId()).orElseThrow(() -> new RessourcesNotFoundException("Agent","agentId",null));
        TypeConge typeConge = typeCongeRepository.findById(congeRequest.getTypeCongeId()).orElseThrow(() -> new RessourcesNotFoundException("TypeConge","TypeCongeId", new Object()));

        Conge c = new Conge();
        c.setCommentaires(congeRequest.getCommentaires());
        c.setDateDeb(congeRequest.getDateDeb());
        c.setDateFin(congeRequest.getDateFin());
        c.setAgent(agent);
        c.setTypeConge(typeConge);

        return congeRepository.save(c);
    }


    // find all congés;
    public List<Conge> findAllConge(){
        return congeRepository.findAll();
    }

    // find one conge
    public Conge findOneConge(String dateDeb, String dateFin) throws RessourcesNotFoundException{

        if(congeRepository.existsByDateDeb(dateDeb) && congeRepository.existsByDateFin(dateFin)){
            return congeRepository.findByDateDeb(dateDeb).get();
        }

       throw new RessourcesNotFoundException("Conge","dateDebut et DateFin",null);
    }


    // delete conge
    public String deleteConge(Long id) throws RessourcesNotFoundException{
        if(!congeRepository.existsById(id))
            throw new RessourcesNotFoundException("Congé", "id" , new Object());

        congeRepository.deleteById(id);
        return "Suppression réussit!";
    }

    // update conge
    public String updateConge(Long id, CongeRequest cr) throws RessourcesNotFoundException, BadRequest{
        Conge conge = congeRepository.findById(id).orElseThrow(() -> new RessourcesNotFoundException("Conge", "Id",new Object()));

        Agent agent  = agentRepository.findById(cr.getAgentId()).orElseThrow(() -> new RessourcesNotFoundException("Agent","agentId",new Object()));
        TypeConge typeConge = typeCongeRepository.findById(cr.getTypeCongeId()).orElseThrow(() -> new RessourcesNotFoundException("TypeConge","TypeCongeId", new Object()));

        conge.setCommentaires(cr.getCommentaires());
        conge.setDateDeb(cr.getDateDeb());
        conge.setDateFin(cr.getDateFin());
        conge.setAgent(agent);
        conge.setTypeConge(typeConge);

        congeRepository.save(conge);
        return "modification réussit ! ";
    }
}
