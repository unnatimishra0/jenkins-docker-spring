package com.nagarro.training.controller;

import com.nagarro.training.entity.User;
import com.nagarro.training.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@CrossOrigin(origins = "http://localhost:4200")
public class UserController {

    @Autowired

    private UserService userService;
    private final String KEY = "ecommerce";

    @PostMapping("/register")
    public ResponseEntity<Object> registerUser(@RequestBody User user, @RequestHeader("API_KEY") String API_KEY) throws Exception {
        if (API_KEY.equalsIgnoreCase(KEY)) {
            String userName = user.getUserName();
            String userEmailId = user.getEmail();

            User userObject = userService.fetchUserByEmail(userEmailId);
            if (userObject != null) {
                return ResponseEntity.ok("User with " + userEmailId + " already exist");
            }


            userObject = userService.fetchUserByUserName(userName);
            if (userObject != null) {
                return ResponseEntity.ok("User with " + userName + " already exist");
            }
            userService.saveUser(user);

            return ResponseEntity.ok("Registration Done");
        } else return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(null);
    }


    @PostMapping(value = "/login")
    public ResponseEntity<Object> loginUser(@RequestBody User user, @RequestHeader("API_KEY") String API_KEY) throws Exception {

        User userObject;
        if (API_KEY.equalsIgnoreCase(KEY)) {
            userObject = userService.fetchUserByEmail(user.getEmail());
            return ResponseEntity.status(HttpStatus.OK).body(userObject);
        } else return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(null);
    }


    @PutMapping("/forget")
    public ResponseEntity<Object> resetUser(@RequestBody User user, @RequestHeader("API_KEY") String API_KEY) throws Exception {
        if (API_KEY.equalsIgnoreCase(KEY)) {
            String userPassword = user.getPassword();
            String userEmailId = user.getEmail();

            User userObject = userService.fetchUserByEmail(userEmailId);
            if (userObject == null || userObject.getRole().equalsIgnoreCase("admin")) {
                return ResponseEntity.ok("User with " + userEmailId + " doesn't exist");
            } else {
                userObject.setPassword(userPassword);
                userService.saveUser(userObject);
            }

            return ResponseEntity.ok("Reset Done");
        } else return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("Wrong API_KEY set in headers");
    }

}
