package com.jk.service;

import com.jk.model.User;

import java.util.List;

public interface UserService {
    List<User> getTodayBirthdayPeopleList();

    void sendMsg(User user);

    void sendEmail(User user);
}
