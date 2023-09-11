package com.maliha.miniproject.controller;

import com.maliha.miniproject.model.User;
import com.maliha.miniproject.model.UserLoginModel;
import com.maliha.miniproject.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class UserController {
    @Autowired
    UserService userService;

    @PostMapping("/user/register")
    public ResponseEntity<User> register (@RequestBody User user) throws Exception {
        return new ResponseEntity<>(userService.createUser(user), HttpStatus.CREATED);
    }

//    @PostMapping("/user/login")
//    public ResponseEntity<User> login (@RequestBody UserLoginModel userLoginModel) throws Exception {
//        return new ResponseEntity<>(userService.loginUser(userLoginModel), HttpStatus.CREATED);
//    }





}
