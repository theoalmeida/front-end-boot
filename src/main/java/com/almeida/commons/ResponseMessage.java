package com.almeida.commons;

import org.springframework.stereotype.Component;

/**
 * Created by theo on 30/10/15.
 */
@Component
public class ResponseMessage {

    private String message;

    private Boolean hasError = Boolean.FALSE;

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public Boolean getHasError() {
        return hasError;
    }

    public void setHasError(Boolean hasError) {
        this.hasError = hasError;
    }
}
