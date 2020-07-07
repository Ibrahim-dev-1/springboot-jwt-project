package com.kratos.jwtSpringBootApplicationWithReact.payloads.responses;

public class ApiResponse {
    private Boolean success;
    private String message;

    public ApiResponse(Boolean success, String message){
        this.message = message;
        this.success = success;
    }

    public void setMessage(String message) {
        this.message = message;
    }
    public String getMessage() { return  this.message; }

    public void setSuccess(Boolean success){
        this.success = success;
    }
    public Boolean getSuccess(){ return this.success; }
}
