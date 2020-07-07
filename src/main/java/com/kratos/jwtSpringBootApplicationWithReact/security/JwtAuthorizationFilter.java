package com.kratos.jwtSpringBootApplicationWithReact.security;

import io.jsonwebtoken.JwtException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@Component
public class JwtAuthorizationFilter extends OncePerRequestFilter {

    private static final Logger  logger = LoggerFactory.getLogger(JwtAuthorizationFilter.class);

    @Autowired
    MyUserDetailService myUserDetailService;

    @Autowired
    JwtTokenUtils jwtTokenUtils;

    @Override
    protected void doFilterInternal(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, FilterChain filterChain) throws ServletException, IOException {
        String token = getTokenFromRequestHeader(httpServletRequest);
        String username = null;
        // check if token is not null
        if(token != null){

            // get username from token
            try{
                username = jwtTokenUtils.getUsernameFromToken(token);
            }catch (JwtException e){
                logger.error("Jwt Error: " + e.getMessage());
            }catch(IllegalArgumentException e){
                e.printStackTrace();
            }catch (NullPointerException e){
                e.printStackTrace();
            }
            // now check if username is not null and security context is null
            if(username != null && SecurityContextHolder.getContext().getAuthentication() == null){
                logger.info("Username debugging in if statement (getContext()).getAuthentication() == null");

                // if info correct, get userPrincipale and check if token is valide or not expired
                UserDetails principal = myUserDetailService.loadUserByUsername(username);
                logger.info("After get principale from loadUserByUsername fonction");

                // check if token is valid
                if(jwtTokenUtils.isTokenValid(token,principal)){
                    logger.info("le Token est valid donc: AUTHORITE = " + principal.getAuthorities());
                    // if token is valid , now connect this princile

                    // create usernamePassword token
                    UsernamePasswordAuthenticationToken usernameToken = new UsernamePasswordAuthenticationToken(
                            principal,null,principal.getAuthorities()
                    );

                    usernameToken.setDetails(new WebAuthenticationDetailsSource().buildDetails(httpServletRequest));
                    // now connect this token and set it to security context
                    SecurityContextHolder.getContext().setAuthentication(usernameToken);
                }

            }


        }

        // now continous ower chaine
        filterChain.doFilter(httpServletRequest,httpServletResponse);
    }

    // get token from request
    public String getTokenFromRequestHeader(HttpServletRequest request){
        String header = request.getHeader(JwtProprieties.HEADER_STRING);
        String token = null;

        if(header != null && header.startsWith(JwtProprieties.TOKEN_STRING)){
            token = header.substring(JwtProprieties.TOKEN_STRING.length());
        }
        return token;
    }
}
