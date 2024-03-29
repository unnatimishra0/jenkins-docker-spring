package com.nagarro.training.service;

import com.nagarro.training.entity.User;

public interface UserService {

    void saveUser(User user);

    User fetchUserByEmail(String emailId);

    User fetchUserByUserName(String uName);


    long countCustomers();
}
