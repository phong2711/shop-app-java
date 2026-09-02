package com.example.shop_app_backend.services;

import com.example.shop_app_backend.dtos.UserDto;
import com.example.shop_app_backend.exceptions.DataNotFoundException;
import com.example.shop_app_backend.models.Role;
import com.example.shop_app_backend.models.User;
import com.example.shop_app_backend.repositories.RoleRepository;
import com.example.shop_app_backend.repositories.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.dao.DataAccessException;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class UserService implements IUserService{
    private final UserRepository userRepository;
    private final RoleRepository roleRepository;
    @Override
    public User createUser(UserDto userDto) throws DataAccessException {
        String phoneNumber = userDto.getPhoneNumber();
        if(userRepository.existsByPhoneNumber(phoneNumber)){
            throw new DataIntegrityViolationException("Phone number already exists");
        }

        User newUser = User.builder()
                .fullName(userDto.getFullName())
                .phoneNumber(userDto.getPhoneNumber())
                .password(userDto.getPassword())
                .address(userDto.getAddress())
                .dateOfBirth(userDto.getDateOfBirth())
                .facebookAccountId(userDto.getFacebookAccountId())
                .googleAccountId(userDto.getGoogleAccountId())
                .build();

        Role role = roleRepository.findById(userDto.getRoleId())
                .orElseThrow(()-> new DataNotFoundException("Role not found"));
        newUser.setRole(role);
        return userRepository.save(newUser);
    }
    @Override
    public String login(String phoneNumber, String password) {
        return "";
    }
}
