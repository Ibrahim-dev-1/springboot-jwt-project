package com.kratos.jwtSpringBootApplicationWithReact.security;

import com.kratos.jwtSpringBootApplicationWithReact.models.Agent;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Arrays;
import java.util.Collection;


public class Principale implements UserDetails {
    private Agent agent;

    public Principale(Agent agent){
        this.agent = agent;
    }

    // création d'un constructeur provisoire
    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return Arrays.asList(new SimpleGrantedAuthority(this.agent.getStatus().getGrade()));
    }

    @Override
    public String getPassword() {
        return this.agent.getCompte().getPassword();
    }

    @Override
    public String getUsername() {
        return this.agent.getCompte().getEmail();
    }

    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return !this.agent.getCompte().getCountLock();
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return true;
    }
}
