package com.kratos.jwtSpringBootApplicationWithReact.payloads.requests;

import javax.validation.Valid;
import javax.validation.constraints.Email;

public class LoginRequest {
    @Email
    private String email;

    private String password;

    public LoginRequest(@Email String email, String password) {
        this.email = email;
        this.password = password;
    }

    public LoginRequest() {
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}
