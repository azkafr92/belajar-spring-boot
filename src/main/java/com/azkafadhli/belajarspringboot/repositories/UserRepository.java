package com.azkafadhli.belajarspringboot.repositories;

import lombok.Getter;
import lombok.Setter;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Map;


@ConfigurationProperties(prefix="user.repository")
@Getter
@Setter
@Repository
public class UserRepository implements IUserRepository{

    private List<Map<String, String>> Users;

    private String userId;
    private String firstName;
    private String lastName;
    private String username;
    private String email;

    public List<Map<String, String>> getUsers() {
        Map<String, String> user1 = Map.of(
                "id", userId,
                "first_name", firstName,
                "last_name", lastName,
                "username", username,
                "email", email
        );
        return List.of(user1);
    }

}
