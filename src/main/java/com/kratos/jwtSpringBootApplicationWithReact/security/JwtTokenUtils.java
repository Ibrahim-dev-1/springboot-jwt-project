package com.kratos.jwtSpringBootApplicationWithReact.security;

import io.jsonwebtoken.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Component;

import java.io.Serializable;
import java.util.Date;

@Component
public class JwtTokenUtils implements Serializable {

    private static final Logger logger = LoggerFactory.getLogger(JwtTokenUtils.class);

    // extract username from jwt token
    public String getUsernameFromToken(String token){
        return getClaimFromToken(token).getSubject();
    }

    // extract expiration date from jwt token
    public Date getExpirationDateFromToken(String token){
       return getClaimFromToken(token).getExpiration();
    }

    // create getClaimFromToken using UserDétailsService
    public Claims getClaimFromToken(String token){
            return Jwts.parser()
                    .setSigningKey(JwtProprieties.secret)
                    .parseClaimsJws(token)
                    .getBody();
    }

    // générate token from userDetails
    public String generateToken(UserDetails principal){
        // a tester si la clet de sécurity de json web token est vide ou null
        logger.info("La clet de sécurity au niveau la fonction generateToken :" + JwtProprieties.secret);
        return Jwts.builder()
                .setSubject(principal.getUsername())
                .setIssuedAt(new Date())
                .setExpiration(new Date(System.currentTimeMillis() * JwtProprieties.JWT_TOKEN_VALIDITY))
                .signWith(SignatureAlgorithm.HS256,JwtProprieties.secret).compact();
    }

    // create is token expired
    public boolean isTokenExpired(String token){
        Date expirationTime = getExpirationDateFromToken(token);
        return (expirationTime.before(new Date()));
    }

    // check if token isvalid
    public boolean isTokenValid(String token, UserDetails principale){
        String username = getUsernameFromToken(token);

        return (username.equals(principale.getUsername()) && isTokenExpired(token));
    }
}
