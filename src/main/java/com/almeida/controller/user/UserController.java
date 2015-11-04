package com.almeida.controller.user;

import com.almeida.Service.UserService;
import com.almeida.commons.ResponseMessage;
import com.almeida.entity.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;
import java.util.Locale;

@RestController
@RequestMapping(value = "user")
public class UserController {


    @Autowired
    private MessageSource messageSource;

    @Autowired
    private UserService userService;

    @Autowired
    ResponseMessage responseMessage;


    @RequestMapping(value = "/save", method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity save(@RequestBody @Valid User user, BindingResult bindingResult) {

        if (bindingResult.hasErrors()) {
            System.out.print("chegou com erro");
        }

        if (user.getEmail() != null) {

            User searchedUser = userService.findUserByEmail(user.getEmail());

            if (searchedUser != null) {
                responseMessage.setMessage(messageSource.getMessage("user.save.duplicate", null, new Locale("pt", "BR")));
                return new ResponseEntity(responseMessage, HttpStatus.ALREADY_REPORTED);
            } else {
                userService.saveUser(user);
                responseMessage.setMessage(messageSource.getMessage("user.save.success", null, new Locale("pt", "BR")));
                return new ResponseEntity(responseMessage, HttpStatus.CREATED);
            }
        }
        return new ResponseEntity(HttpStatus.INTERNAL_SERVER_ERROR);
    }
}
