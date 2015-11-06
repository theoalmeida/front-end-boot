package com.almeida.commons;

import org.springframework.beans.factory.config.BeanDefinition;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import java.io.Serializable;

/**
 * Created by theo on 04/11/15.
 */

@Component
@Scope(value = BeanDefinition.SCOPE_PROTOTYPE)
public class Message implements Serializable {

    private TypeMessage typeMessage;

    private String message;

    public Message() {
    }

    public Message(String message, TypeMessage typeMessage) {
        this.message = message;
        this.typeMessage = typeMessage;
    }

    public String getMessage() {
        return message;
    }


    public TypeMessage getTypeMessage() {
        return typeMessage;
    }
}
