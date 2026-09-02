package com.example.shop_app_backend.services;

import com.example.shop_app_backend.dtos.UserDto;
import com.example.shop_app_backend.models.User;
import org.springframework.dao.DataAccessException;

public interface IUserService {
    User createUser(UserDto userDto) throws DataAccessException;
    String login(String phoneNumber, String password);
}
