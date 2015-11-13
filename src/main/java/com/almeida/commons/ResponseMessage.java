package com.almeida.commons;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.config.BeanDefinition;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;
import org.springframework.util.CollectionUtils;
import org.springframework.validation.BindingResult;
import org.springframework.validation.ObjectError;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by theo on 30/10/15.
 */
@Component
@Scope(value = BeanDefinition.SCOPE_PROTOTYPE)
public class ResponseMessage implements Serializable {

    private List<Message> message = new ArrayList<>();

    public void addMessage(BindingResult bindingResult) {
        bindingResult.getFieldError().toString();
        for (ObjectError error : bindingResult.getAllErrors()) {
            this.message.add(new Message("Campo :" + bindingResult.getFieldError().getField() + " : " + bindingResult.getFieldError().getDefaultMessage(),
                    TypeMessage.ERROR));
        }
    }

    public void addMessage(String message, TypeMessage typeMessage) {
        this.message.add(new Message(message, typeMessage));
    }

    public List<Message> getMessage() {
        return message;
    }

    public void setMessage(List<Message> message) {
        this.message = message;
    }
}
