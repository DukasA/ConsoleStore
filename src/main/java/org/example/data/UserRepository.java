package org.example.data;

import org.example.model.User;

import java.util.ArrayList;
import java.util.List;

public class UserRepository {
    private static UserRepository instance = null;
    private List<User> users;

    private UserRepository() {
        users = new ArrayList<>();
    }

    public static UserRepository getInstance() {
        if (instance == null) {
            instance = new UserRepository();
        }
        return instance;
    }

    public void addUser(User user) {
        users.add(user);
    }

    public User getUserByUsername(String username) {
        for (User user : users) {
            if (user.getUsername().equals(username)) {
                return user;
            }
        }
        return null;
    }

    public List<User> getUsers() {
        return users;
    }

}
