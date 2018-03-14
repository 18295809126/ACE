package com.jk.service;

import com.jk.model.User;

public class EmailThread implements Runnable{

    private UserService userService;
    private User user;
    @Override
    public void run() {
        userService.sendEmail(user);
    }

    public UserService getUserService() {
        return userService;
    }

    public void setUserService(UserService userService) {
        this.userService = userService;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public EmailThread(UserService userService, User user) {
        this.userService = userService;
        this.user = user;
    }

    public EmailThread() {
    }
}
