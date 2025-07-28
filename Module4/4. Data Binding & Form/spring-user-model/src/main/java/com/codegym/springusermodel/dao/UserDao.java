package com.codegym.springusermodel.dao;

import com.codegym.springusermodel.model.Login;
import com.codegym.springusermodel.model.User;

import java.util.ArrayList;
import java.util.List;

public class UserDao {
    private static List<User> users;

    static {
        users \= new ArrayList<>();
        users.add(new User("john", "123456", "John Doe", "john@example.com", 25));
        users.add(new User("jane", "654321", "Jane Smith", "jane@example.com", 30));
        users.add(new User("mike", "password", "Mike Johnson", "mike@example.com", 22));
    }

    public static User checkLogin(Login login) {
        for (User user : users) {
            if (user.getAccount().equals(login.getAccount())
                    && user.getPassword().equals(login.getPassword())) {
                return user;
            }
        }
        return null;
    }
}