package com.almeida.Service;

import com.almeida.entity.Validation.GoogleResponseCaptcha;
import com.almeida.entity.user.User;
import com.almeida.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;

import javax.transaction.Transactional;

/**
 * Created by theo on 04/11/15.
 */
@Service
@Transactional
public class UserService {

    public static final String RECAPTCHA_API_SITEVERIFY = "/recaptcha/api/siteverify";
    @Autowired
    UserRepository userRepository;

    private RestTemplate restTemplate = new RestTemplate();

    private String secret = "6LcmXhATAAAAAPBVf9nOvmDJNz0T4qxpOxkHTvEO";

    public void saveUser(User user) {
        user.setEnabled(Boolean.TRUE);
        this.userRepository.save(user);
    }

    public void verifyDDOSAtack(String response) {

        String targetUrl = UriComponentsBuilder.fromUriString("https://www.google.com")
                .path(RECAPTCHA_API_SITEVERIFY)
                .queryParam("secret", secret)
                .queryParam("response", response)
                .build()
                .toUriString();

        GoogleResponseCaptcha googleResponse = restTemplate.getForObject(targetUrl, GoogleResponseCaptcha.class);
        System.out.print(googleResponse.getSuccess());
    }

    public User findUserByEmail(String email) {
        return this.userRepository.findByEmail(email);
    }


}
