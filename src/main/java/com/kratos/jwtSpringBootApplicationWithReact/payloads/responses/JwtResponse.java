package com.kratos.jwtSpringBootApplicationWithReact.payloads.responses;

public class JwtResponse {
    private Boolean success;
    private String token;

    // Constructeur
    public JwtResponse(Boolean success, String token){
        this.token = token;
        this.success = success;
    }

    public JwtResponse() {
    }

    public Boolean getSuccess() {
        return success;
    }

    public void setSuccess(Boolean success) {
        this.success = success;
    }

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }
}
