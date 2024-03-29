package com.nagarro.training.service;

import com.nagarro.training.entity.User;
import com.nagarro.training.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserServiceImpl implements UserService {

    @Autowired
    private UserRepository userRepository;

    @Override
    public void saveUser(User user) {
        userRepository.save(user);

    }

    @Override
    public User fetchUserByEmail(String emailId) {
        return userRepository.findByEmail(emailId);

    }

    @Override
    public User fetchUserByUserName(String uName) {
        return userRepository.findByUserName(uName);

    }


    @Override
    public long countCustomers() {
        return userRepository.countCustomers();
    }

}
