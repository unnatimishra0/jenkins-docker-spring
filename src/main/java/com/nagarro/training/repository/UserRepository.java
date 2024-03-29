package com.nagarro.training.repository;

import com.nagarro.training.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

public interface UserRepository extends JpaRepository<User, Integer> {

    User findByEmail(String emailId);

    User findByEmailAndPassword(String emailId, String password);

    User findByUserName(String uName);

    @Query(value = "SELECT COUNT(id) AS customers FROM User u WHERE u.role= 'customer'", nativeQuery = true)
    long countCustomers();

}
