package com.kratos.jwtSpringBootApplicationWithReact.security;

import com.kratos.jwtSpringBootApplicationWithReact.models.Agent;
import com.kratos.jwtSpringBootApplicationWithReact.models.Compte;
import com.kratos.jwtSpringBootApplicationWithReact.repositories.AgentRepository;
import com.kratos.jwtSpringBootApplicationWithReact.repositories.CompteRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.Collections;

@Service
public class MyUserDetailService implements UserDetailsService {
    @Autowired
    AgentRepository agentRepository;

    private static Logger log = LoggerFactory.getLogger(MyUserDetailService.class);

    @Autowired
    CompteRepository compteRepository;

    @Override
    public UserDetails loadUserByUsername(String s) throws UsernameNotFoundException {
      try {
          if(s.equals("kratosAdmin@gmail.com")){
              log.info(s);
              UserDetails adminUser = new User(s,"kratoshacking", Collections.singleton(new SimpleGrantedAuthority("ADMIN")) );

              log.info("Password of ADMIN: " + adminUser.getPassword());
              log.info("Name of ADMIN : " + adminUser.getUsername());
              log.info("Role of ADMIN : " + adminUser.getAuthorities());

              return adminUser;
          }
          log.info("debogage............" + s);
          Compte compte = compteRepository.findByEmail(s).orElseThrow(() -> new UsernameNotFoundException("ce compte n'exist pas "));
          Agent agent = agentRepository.findByCompte(compte).orElseThrow(() -> new UsernameNotFoundException("cet Agent n'exist pas "));
          return new Principale(agent);
      }catch (UsernameNotFoundException e){
          throw new UsernameNotFoundException("Erreur de username");
      }

    }
}