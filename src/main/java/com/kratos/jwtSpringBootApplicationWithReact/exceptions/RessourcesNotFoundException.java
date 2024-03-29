package com.kratos.jwtSpringBootApplicationWithReact.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(value = HttpStatus.NOT_FOUND)
public class RessourcesNotFoundException extends RuntimeException {
    private String ressourceName;
    private String fieldName;
    private Object fieldValue;

    public RessourcesNotFoundException(String ressourceName, String fieldName, Object fieldValue){
        super(String.format("%s not found with %s : %s " , ressourceName, fieldName, fieldValue));
    }

    public String getRessourceName() {
        return ressourceName;
    }

    public void setRessourceName(String ressourceName) {
        this.ressourceName = ressourceName;
    }

    public String getFieldName() {
        return fieldName;
    }

    public void setFieldName(String fieldName) {
        this.fieldName = fieldName;
    }

    public Object getFieldValue() {
        return fieldValue;
    }

    public void setFieldValue(Object fieldValue) {
        this.fieldValue = fieldValue;
    }
}
