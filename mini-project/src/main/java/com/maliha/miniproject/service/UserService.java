package com.maliha.miniproject.service;

import com.maliha.miniproject.model.UserDto;

public interface UserService {
    UserDto createUser(UserDto userDto) throws Exception;
    UserDto getUserById(Integer id) throws NullPointerException;
    UserDto getUser(String email);
}
