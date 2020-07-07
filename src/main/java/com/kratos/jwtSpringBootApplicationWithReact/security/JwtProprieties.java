package com.kratos.jwtSpringBootApplicationWithReact.security;

import org.springframework.beans.factory.annotation.Value;

public class JwtProprieties {
    // expiration token time
    public static final long JWT_TOKEN_VALIDITY = 5*60*60;

    // token string
    public static String TOKEN_STRING  = "Bearer ";

    // get string header
    public static String HEADER_STRING  = "Authorization";

    // get secret number from spring boot propriety
    public static String secret = "projet bts";

}
