package com.maliha.miniproject.service;

import com.maliha.miniproject.model.User;

public interface UserService {
    User createUser(User user) throws Exception;
    User getUserById(Integer id) throws NullPointerException;
}
