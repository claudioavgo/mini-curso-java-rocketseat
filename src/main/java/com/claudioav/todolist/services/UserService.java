package com.claudioav.todolist.services;

import com.claudioav.todolist.models.UserModel;
import com.claudioav.todolist.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserService {

    @Autowired
    private UserRepository userRepository;

    public UserModel create(UserModel newUser) {
        if (userRepository.findByUsername(newUser.getUsername()) != null) {
            return null;
        }



        return userRepository.save(newUser);
    }

    public UserModel findByUsername(String username) {
        return userRepository.findByUsername(username);
    }
}
