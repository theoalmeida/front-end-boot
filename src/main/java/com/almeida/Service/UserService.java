package com.almeida.Service;

import com.almeida.entity.User;
import com.almeida.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import javax.transaction.Transactional;

/**
 * Created by theo on 04/11/15.
 */
@Service
@Transactional
public class UserService {

    @Autowired
    UserRepository userRepository;

    public void saveUser(User user) {
        user.setEnabled(Boolean.TRUE);
        this.userRepository.save(user);
    }

    public User findUserByEmail(String email){
       return this.userRepository.findByEmail(email);
    }

}
