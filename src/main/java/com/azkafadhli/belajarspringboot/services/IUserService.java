package com.azkafadhli.belajarspringboot.services;

import java.util.List;
import java.util.Map;

public interface IUserService {
    List<Map<String, String>> getUsers();
}
