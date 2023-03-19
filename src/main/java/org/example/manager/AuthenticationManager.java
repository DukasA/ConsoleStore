package org.example.manager;

import org.example.data.UserRepository;
import org.example.model.User;

import static org.example.model.User.hashPassword;

public class AuthenticationManager {
    private static AuthenticationManager instance = null;
    private UserRepository userRepository;

    private AuthenticationManager() {
        userRepository = UserRepository.getInstance();
    }

    public static AuthenticationManager getInstance() {
        if (instance == null) {
            instance = new AuthenticationManager();
        }
        return instance;
    }

    public boolean register(String username, String password) {
        if (userRepository.getUserByUsername(username) != null) {
            return false; // wze je
        }
        User user = new User(username, hashPassword(password));
        userRepository.addUser(user);
        return true;
    }

    public User login(String username, String password) {
        User user = userRepository.getUserByUsername(username);
        if (user == null) {
            return null; // niema menchika
        }
        String hashedPassword = hashPassword(password);
        if (!user.checkPassword(hashedPassword)) {
            return null; // parol ne parol
        }
        return user;
    }
}
