package com.azkafadhli.belajarspringboot.services;

import com.azkafadhli.belajarspringboot.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

@Service
public class UserService implements IUserService{

    @Autowired
    private UserRepository userRepository;

    public List<Map<String, String>> getUsers() {
        return userRepository.getUsers();
    }
}
