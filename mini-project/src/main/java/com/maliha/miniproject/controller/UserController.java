package com.maliha.miniproject.controller;

import com.maliha.miniproject.constants.AppConstants;
import com.maliha.miniproject.model.BorrowBook;
import com.maliha.miniproject.model.UserDto;
import com.maliha.miniproject.model.UserLoginModel;
import com.maliha.miniproject.service.UserService;
import com.maliha.miniproject.utils.JWTUtils;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.Map;

@RestController
public class UserController {
    @Autowired
    UserService userService;

    @Autowired
    private AuthenticationManager authenticationManager;

    @Autowired
    private JWTUtils jwtUtils;

//    @PostMapping("/user/register")
//    public ResponseEntity<UserDto> register (@RequestBody UserDto userDto) throws Exception {
//        return new ResponseEntity<UserDto>(userService.createUser(userDto), HttpStatus.CREATED);
//    }
@PostMapping("/user/register")
public ResponseEntity<?> register (@RequestBody UserDto userDto) {
    try {
        UserDto createdUser = userService.createUser(userDto);
        String accessToken = JWTUtils.generateToken(createdUser.getEmail());
        Map<String, Object> registerResponse = new HashMap<>();
        registerResponse.put("user", createdUser);
        registerResponse.put(AppConstants.HEADER_STRING, AppConstants.TOKEN_PREFIX + accessToken);
        return ResponseEntity.status(HttpStatus.CREATED).body(registerResponse);
    } catch (Exception e) {
        return new ResponseEntity<>(e.getMessage(),HttpStatus.BAD_REQUEST);
    }
}

//    @PostMapping("/user/login")
//    public String login (@RequestBody UserLoginModel userLoginModel) throws Exception {
//        Authentication authentication = authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(userLoginModel.getEmail(), userLoginModel.getPassword()));
//        if (authentication.isAuthenticated()) {
//            return jwtUtils.generateToken(userLoginModel.getEmail());
//        }
//        else {
//            throw new UsernameNotFoundException("invalid user request !");
//        }
//    }
@PostMapping("/user/login")
public ResponseEntity<?> login(@RequestBody UserLoginModel userLoginModel, HttpServletResponse response) {
    try {
        authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(userLoginModel.getEmail(), userLoginModel.getPassword()));
        UserDto userDto = userService.getUser(userLoginModel.getEmail());
        String accessToken = JWTUtils.generateToken(userDto.getEmail());
        Map<String, Object> loginResponse = new HashMap<>();
        loginResponse.put("userId", userDto.getUserId());
        loginResponse.put("email", userDto.getEmail());
        loginResponse.put(AppConstants.HEADER_STRING, AppConstants.TOKEN_PREFIX + accessToken);
        return ResponseEntity.status(HttpStatus.OK).body(loginResponse);

    } catch (BadCredentialsException e) {
        return new ResponseEntity<>("Wrong password!", HttpStatus.UNAUTHORIZED);
    }
    catch (Exception e) {
        return new ResponseEntity<>("Wrong Email or other exception!", HttpStatus.UNAUTHORIZED);

    }
}


    @GetMapping("/users/{userId}")
    public ResponseEntity<?> getUser(@PathVariable Integer userId){
        try {
            return new ResponseEntity<UserDto>(userService.getUserById(userId),HttpStatus.OK);
//            return new ResponseEntity<>(user, HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(e.getMessage(), HttpStatus.BAD_REQUEST);
        }

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
