package com.kratos.jwtSpringBootApplicationWithReact.services;

import com.kratos.jwtSpringBootApplicationWithReact.exceptions.BadRequest;
import com.kratos.jwtSpringBootApplicationWithReact.models.Calendrier;
import com.kratos.jwtSpringBootApplicationWithReact.payloads.requests.CalendrierRequest;
import com.kratos.jwtSpringBootApplicationWithReact.repositories.CalendrierRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.sql.Time;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.TimeZone;

@Service
public class CalendrierService {
    @Autowired
    CalendrierRepository repo;

    // get All calendrier
    public List<Calendrier> getAll(){
        return repo.findAll();
    }

    // add Calendrier
    public Calendrier addCalendrier(CalendrierRequest calendrier) throws BadRequest, ParseException {
        Date now = new Date();
        Calendar cal = Calendar.getInstance();
        cal.setTime(now);
        int nowYear = cal.get(Calendar.YEAR);


        SimpleDateFormat formatter = new SimpleDateFormat("dd-MM-yyyy");
        Date deb = formatter.parse(calendrier.getDateDebut());
        Date fin = formatter.parse(calendrier.getDateFin());
        cal.setTime(deb);
        int debYear = cal.get(Calendar.YEAR);

        cal.setTime(fin);
        int finYear = cal.get(Calendar.YEAR);

        if(deb.after(fin)){
            throw new BadRequest("La date debut ne doit pas venir après la date fin!");
        }

        if(nowYear != debYear || nowYear != finYear ){
            throw new BadRequest("Vous devez choisir la date dans l'année courrante");
        }

        if(repo.existsByDateDebutAndDateFin(deb, fin)){
            throw new BadRequest("ce calendrier exist déja");
        }

        Calendrier newCalendrier = new Calendrier(deb,fin);
        return repo.save(newCalendrier);
    }

    // update calendrier
    public String update(int id , Calendrier c ) throws Exception{
        List<Calendrier> calendriers = repo.findDateDebutAndDateFin(c.getDateDebut(), c.getDateFin());

        if(!calendriers.isEmpty())
            throw new Exception("ce calendrier exist déja");

        Calendrier calendrier = repo.findById(id).orElseThrow(() -> new Exception("ce Calendrier n'exist pas ! "));
        calendrier.setDateDebut(c.getDateDebut());
        calendrier.setDateFin(c.getDateFin());
        calendrier.setNbrJr(c.getNbrJr());

        repo.save(calendrier);
        return "Modification réussi!";
    }

    // delete calendrier
    public String deleteCalendrier(int id) throws Exception{
        if(!repo.existsById(id) )
            throw new Exception("ce calendrier n'exist pas ! ");

        repo.deleteById(id);
        return "Suppression réussit !";
    }
}
