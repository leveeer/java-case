package com.jxau.manager;

import com.jxau.util.PasswordException;
import com.jxau.util.UserNotFoundException;

public class UserManager {
    private static UserManager instance = new UserManager();

    private UserManager() {}

    public static UserManager getinstance() {
        return instance;
    }

    public void login(String username, String password) throws RuntimeException{
        if (!"admin".equals(username)) {
            throw new UserNotFoundException();
        }
        if (!"admin".equals(password)) {
            throw new PasswordException();
        }
    }
}
