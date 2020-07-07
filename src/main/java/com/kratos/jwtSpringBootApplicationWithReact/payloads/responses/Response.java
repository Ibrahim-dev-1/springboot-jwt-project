package com.kratos.jwtSpringBootApplicationWithReact.payloads.responses;


import java.net.URI;

public class Response {
    private Boolean error;
    private String message;
    private Object data;
    private URI path;

    public Response(){
        this.error = false;
        this.message = "success";
        this.data = null;
    }
    public Response(Boolean error, String message){
        this.error = error;
        this.message = message;
        this.data = null;
    }

    public Response(String message,Object data){
        this.error = false;
        this.message = message;
        this.data = data;
    }
    public Response(Boolean error, String message, Object data, URI path){
        this.error = error;
        this.message = message;
        this.data = data;
        this.path = path;
    }

    public Boolean getError() {
        return error;
    }

    public void setError(Boolean error) {
        this.error = error;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public Object getData() {
        return data;
    }

    public void setData(Object data) {
        this.data = data;
    }

    public URI getPath() {
        return path;
    }

    public void setPath(URI path) {
        this.path = path;
    }
}
