package com.almeida.controller.user;

import com.almeida.Service.UserService;
import com.almeida.commons.ResponseMessage;
import com.almeida.commons.TypeMessage;
import com.almeida.entity.user.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.config.BeanDefinition;
import org.springframework.context.MessageSource;
import org.springframework.context.annotation.Scope;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.provisioning.UserDetailsManager;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;
import java.io.IOException;
import java.util.Locale;

@RestController
@RequestMapping(value = "user")
@Scope(value = BeanDefinition.SCOPE_PROTOTYPE)
public class UserController {

    @Autowired
    private MessageSource messageSource;

    @Autowired
    private UserService userService;

    @Autowired
    private ResponseMessage responseMessage;


    @RequestMapping(value = "/save", method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity save(@RequestBody @Valid User user, BindingResult bindingResult, HttpServletRequest request) throws IOException {

        if (bindingResult.hasErrors() && userService.isRobootAtack(user.getCaptchaValue())) {
            responseMessage.addMessage(bindingResult);
            return new ResponseEntity(responseMessage, HttpStatus.NOT_ACCEPTABLE);
        }

        if (user.getEmail() != null) {

            User searchedUser = userService.findUserByEmail(user.getEmail());

            if (searchedUser != null) {
                responseMessage.addMessage(messageSource.getMessage("user.save.duplicate", null, new Locale("pt", "BR")), TypeMessage.ERROR);
                return new ResponseEntity(responseMessage, HttpStatus.ALREADY_REPORTED);
            } else {
                User userSaved = userService.save(user);
                userService.addUserToSession(userSaved);
                responseMessage.addMessage(messageSource.getMessage("user.save.success", null, new Locale("pt", "BR")), TypeMessage.SUCCESS);
                return new ResponseEntity(responseMessage, HttpStatus.OK);
            }
        }
        return new ResponseEntity(HttpStatus.INTERNAL_SERVER_ERROR);
    }


}
