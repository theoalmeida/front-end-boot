package com.almeida.Service;

import com.almeida.entity.Validation.GoogleResponseCaptcha;
import com.almeida.entity.user.Authorities;
import com.almeida.entity.user.Role;
import com.almeida.entity.user.User;
import com.almeida.repository.AuthorityRepository;
import com.almeida.repository.UserRepository;
import org.omg.CORBA.PRIVATE_MEMBER;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.provisioning.UserDetailsManager;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;

import javax.transaction.Transactional;
import java.util.ArrayList;
import java.util.Collection;
import java.util.HashSet;
import java.util.List;

/**
 * Created by theo on 04/11/15.
 */
@Service
@Transactional
public class UserService {

    public static final String RECAPTCHA_API_SITEVERIFY = "/recaptcha/api/siteverify";

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private AuthorityRepository authorityRepository;

    @Autowired
    private UserDetailsManager userDetailsManager;

    private RestTemplate restTemplate = new RestTemplate();

    private String secret = "6LcmXhATAAAAAPBVf9nOvmDJNz0T4qxpOxkHTvEO";

    @Transactional
    public User save(User user) {
        user.setEnabled(Boolean.TRUE);
        User savedUser =  this.userRepository.save(user);
        this.authorityRepository.save(new Authorities(Role.ROLE_USER, user.getUsername()));
        return  savedUser;
    }

    public void addUserToSession(User savedUser) {
        UserDetails userDetails = userDetailsManager.loadUserByUsername(savedUser.getUsername());
        Authentication auth = new UsernamePasswordAuthenticationToken(userDetails, null, userDetails.getAuthorities());
        SecurityContextHolder.getContext().setAuthentication(auth);
    }

    public Boolean isRobootAtack(String response) {

        if(response == null){
            return Boolean.TRUE;
        }

        String targetUrl = UriComponentsBuilder.fromUriString("https://www.google.com")
                .path(RECAPTCHA_API_SITEVERIFY)
                .queryParam("secret", secret)
                .queryParam("response", response)
                .build()
                .toUriString();

        GoogleResponseCaptcha googleResponse = restTemplate.getForObject(targetUrl, GoogleResponseCaptcha.class);
        return !googleResponse.getSuccess();
    }

    public User findUserByEmail(String email) {
        return this.userRepository.findByEmail(email);
    }


}
