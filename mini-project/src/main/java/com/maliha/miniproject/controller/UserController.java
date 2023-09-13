package com.maliha.miniproject.controller;

import com.maliha.miniproject.model.BorrowBook;
import com.maliha.miniproject.model.UserDto;
import com.maliha.miniproject.model.UserLoginModel;
import com.maliha.miniproject.service.UserService;
import com.maliha.miniproject.utils.JWTUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.web.bind.annotation.*;

@RestController
public class UserController {
    @Autowired
    UserService userService;

    @Autowired
    private AuthenticationManager authenticationManager;

    @Autowired
    private JWTUtils jwtUtils;

    @PostMapping("/user/register")
    public ResponseEntity<UserDto> register (@RequestBody UserDto userDto) throws Exception {
        return new ResponseEntity<UserDto>(userService.createUser(userDto), HttpStatus.CREATED);
    }

    @PostMapping("/user/login")
    public String login (@RequestBody UserLoginModel userLoginModel) throws Exception {
        Authentication authentication = authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(userLoginModel.getEmail(), userLoginModel.getPassword()));
//        return new ResponseEntity<>(userService.loginUser(userLoginModel), HttpStatus.CREATED);
        if (authentication.isAuthenticated()) {
            return jwtUtils.generateToken(userLoginModel.getEmail());
        }
        else {
            throw new UsernameNotFoundException("invalid user request !");
        }
    }


    @GetMapping("/users/{userId}")
    public ResponseEntity<UserDto> getUser(@PathVariable Integer userId){
        return new ResponseEntity<UserDto>(userService.getUserById(userId),HttpStatus.OK);
    }

    @GetMapping("/users/{userId}/books")
    public ResponseEntity<BorrowBook> getBorrowedBooks(){
        return null;
    }

    @GetMapping("/users/{userId}/borrowed-books")
    public ResponseEntity<BorrowBook> getAllBorrowedBooks(){
        return null;
    }


}
