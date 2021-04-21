package com.nlobo.ideas.services;

import com.nlobo.ideas.models.User;
import com.nlobo.ideas.repositories.UserRepository;
import org.mindrot.jbcrypt.BCrypt;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class UserService {
    private final UserRepository userRepository;
    public UserService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    public User registerUser(User user) {
        String hashedPW = BCrypt.hashpw(user.getPassword(), BCrypt.gensalt());
        user.setPassword(hashedPW);
        return userRepository.save(user);
    }

    public User findByEmail(String email) {
        return userRepository.findByEmail(email);
    }

    public User findUserById(Long id) {
        Optional<User> optionalUser = userRepository.findById(id);
        if(optionalUser.isPresent()) {
            return optionalUser.get();
        } else {
            return null;
        }
    }

    public boolean authenticateUser(String email, String password) {
        User user = userRepository.findByEmail(email);
        if(user == null) {
            return false;
        } else {
            if(BCrypt.checkpw(password, user.getPassword())) {
                return true;
            } else {
                return false;
            }
        }
    }

    public boolean emailExists(String email) {
        List<User> existingUsers = userRepository.findAll();
        for (User existingUser : existingUsers) {
            if (email.equals(existingUser.getEmail())) {
                return true;
            }
        }
        return false;
    }
}
