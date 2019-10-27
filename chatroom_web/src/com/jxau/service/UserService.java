package com.jxau.service;

import com.jxau.domain.User;

public interface UserService {
    public abstract User findUserByUsernameAndPassword(User user);
}
