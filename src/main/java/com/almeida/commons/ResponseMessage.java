package com.almeida.commons;

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

    @Autowired
    private List<Message> message;

    public void setMessages(BindingResult bindingResult) {
        bindingResult.getFieldError().toString();
        for (ObjectError error : bindingResult.getAllErrors()) {
            this.message.add(new Message("Campo :" + bindingResult.getFieldError().getField() + " : " + bindingResult.getFieldError().getDefaultMessage(),
                    TypeMessage.ERROR));
        }
    }

    public void setMessages(String message, TypeMessage typeMessage) {
        this.message.add(new Message(message, typeMessage));

    }

    public List<Message> getMessage() {
        return message;
    }


}
