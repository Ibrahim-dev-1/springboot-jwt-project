package com.kratos.jwtSpringBootApplicationWithReact.controler;

import com.kratos.jwtSpringBootApplicationWithReact.payloads.requests.LoginRequest;
import com.kratos.jwtSpringBootApplicationWithReact.payloads.responses.JwtResponse;
import com.kratos.jwtSpringBootApplicationWithReact.security.JwtTokenUtils;
import com.kratos.jwtSpringBootApplicationWithReact.security.MyUserDetailService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
@RequestMapping("/")
public class Authentication {

    @Autowired
    JwtTokenUtils jwtTokenUtils;

    @Autowired
    MyUserDetailService myUserDetailService;

    AuthenticationManager authenticationManager;

    @PostMapping("/login")
    public ResponseEntity<?> login(@Valid @RequestBody LoginRequest loginRequest){
       try{
           authentication(loginRequest.getEmail(),loginRequest.getPassword());
           UserDetails userDetails = (UserDetails)myUserDetailService.loadUserByUsername(loginRequest.getEmail());
           String token = jwtTokenUtils.generateToken(userDetails);
           return  ResponseEntity.ok(new JwtResponse(true,token));
       }catch (Exception e){
           return ResponseEntity.badRequest().body(e);
       }
    }

    public void authentication(String username , String password){
        try{
            UsernamePasswordAuthenticationToken usernameToken =  new UsernamePasswordAuthenticationToken(username,password);
            authenticationManager.authenticate(usernameToken);
            SecurityContextHolder.getContext().setAuthentication(usernameToken);
        }catch (BadCredentialsException ex){
            ex.printStackTrace();
            throw new BadCredentialsException("Mauvaise informations de connection: " + ex.getMessage());
        }
    }

}
