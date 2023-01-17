package com.azkafadhli.belajarspringboot.repositories;

import java.util.List;
import java.util.Map;

public interface IUserRepository {

    List<Map<String, String>> getUsers();

}
