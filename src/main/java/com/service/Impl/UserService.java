package main.java.com.service.Impl;

import main.java.com.domain.User;

public interface UserService {

    boolean hasMatchUser(String userName, String password);

    User findUserByUserName(String userName);

    void loginSuccess(User user);
}
