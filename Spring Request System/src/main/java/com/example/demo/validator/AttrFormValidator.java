package com.example.demo.validator;

import javax.validation.constraints.NotBlank;

public class AttrFormValidator {

    
    @NotBlank(message = "Please explain the reasoning behind this request!")
    private String reason;

    public AttrFormValidator() {
        
    }  

    public String getReason() {
        return this.reason;
    }

    public void setReason(String reason) {
        this.reason = reason;
    }

}
