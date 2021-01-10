package com.bluebik.shorturl.service;

import com.bluebik.shorturl.dao.jpa.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.bluebik.shorturl.domain.User;
import com.bluebik.shorturl.domain.SuccessResponse;
import com.bluebik.shorturl.exception.UserNotFoundException;
import java.util.Arrays;
import java.util.List;
import org.springframework.http.HttpStatus;
import org.springframework.security.crypto.password.PasswordEncoder;

@Service
public class UserService {
    
    @Autowired
    private UserRepository userRepository;
    
    @Autowired
    private PasswordEncoder passwordEncoder;

    public SuccessResponse findAll() {
        List<User> userResult = userRepository.findAll();
        List<String> list = Arrays.asList();
        HttpStatus status = HttpStatus.OK;
        return new SuccessResponse(status.getReasonPhrase(), status.value(), list, userResult);
    }
    
    public SuccessResponse findUserById(Integer id) {
        User userResult = userRepository.findUserById(id);
        if (userResult == null) {
            throw new UserNotFoundException("User not found in database");
        }
        List<String> list = Arrays.asList();
        HttpStatus status = HttpStatus.OK;
        return new SuccessResponse(status.getReasonPhrase(), status.value(), list, userResult);
    }
    
    public SuccessResponse insert(User user) {
        user.setPassword(passwordEncoder.encode(user.getPassword()));
        User userResult = userRepository.save(user);
        List<String> list = Arrays.asList();
        HttpStatus status = HttpStatus.OK;
        return new SuccessResponse(status.getReasonPhrase(), status.value(), list, userResult);
    }
    
    public SuccessResponse auth(String username, String password) {
        User user = userRepository.findByUsername(username);
        if (user == null) {
            throw new UserNotFoundException("User not found in database");
        }
        
        if (!passwordEncoder.matches(password, user.getPassword())) {
            throw new UserNotFoundException("Password not match");
        }
        
        List<String> list = Arrays.asList();
        HttpStatus status = HttpStatus.OK;
        return new SuccessResponse(status.getReasonPhrase(), status.value(), list, user);
    }
}
