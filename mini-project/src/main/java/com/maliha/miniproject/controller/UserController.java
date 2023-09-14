package com.maliha.miniproject.controller;

import com.maliha.miniproject.constants.AppConstants;
import com.maliha.miniproject.entity.BorrowBookEntity;
import com.maliha.miniproject.model.BorrowBook;
import com.maliha.miniproject.model.UserDto;
import com.maliha.miniproject.model.UserLoginModel;
import com.maliha.miniproject.service.BorrowBookService;
import com.maliha.miniproject.service.UserService;
import com.maliha.miniproject.service.UserServiceImplementation;
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
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/users")
public class UserController {
    @Autowired
    private UserServiceImplementation userServiceImplementation;

    @Autowired
    private BorrowBookService borrowBookService;

    @Autowired
    private AuthenticationManager authenticationManager;

    @Autowired
    private JWTUtils jwtUtils;

@PostMapping("/register")
public ResponseEntity<?> register (@RequestBody UserDto userDto) throws RuntimeException{
    try {
        UserDto createdUser = userServiceImplementation.createUser(userDto);
        String accessToken = JWTUtils.generateToken(createdUser.getEmail());
        Map<String, Object> registerResponse = new HashMap<>();
        registerResponse.put("user", createdUser);
        registerResponse.put(AppConstants.HEADER_STRING, AppConstants.TOKEN_PREFIX + accessToken);
        return ResponseEntity.status(HttpStatus.CREATED).body(registerResponse);
    } catch (RuntimeException e) {
        return new ResponseEntity<>("Cannot create user with this email",HttpStatus.BAD_REQUEST);
    }
}

@PostMapping("/login")
public ResponseEntity<?> login(@RequestBody UserLoginModel userLoginModel, HttpServletResponse response) {
    try {
        authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(userLoginModel.getEmail(), userLoginModel.getPassword()));
        UserDto userDto = userServiceImplementation.getUser(userLoginModel.getEmail());
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


    @GetMapping("/{userId}")
    public ResponseEntity<?> getUser(@PathVariable Integer userId){
        try {
            return new ResponseEntity<UserDto>(userServiceImplementation.getUserById(userId),HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(e.getMessage(), HttpStatus.BAD_REQUEST);
        }

    }

    @GetMapping("/{userId}/books")
    public ResponseEntity<List<BorrowBook>> getAllBorrowedBooks(@PathVariable Integer userId){
        return new ResponseEntity<List<BorrowBook>>(borrowBookService.borrowBookAllWithUserId(userId),HttpStatus.OK);
    }

    @GetMapping("/{userId}/borrowed-books")
    public ResponseEntity<List<BorrowBookEntity>> getBorrowedBooks(@PathVariable Integer userId){
        return new ResponseEntity<List<BorrowBookEntity>>(borrowBookService.getBorrowedBook(userId),HttpStatus.OK);
    }


    @GetMapping("/{userId}/history")
    public ResponseEntity<List<BorrowBookEntity>> getHistory(@PathVariable Integer userId){
        return new ResponseEntity<List<BorrowBookEntity>>(borrowBookService.getHistory(userId),HttpStatus.OK);
    }


}
