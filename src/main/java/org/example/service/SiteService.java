package org.example.service;

import com.fasterxml.jackson.core.JsonProcessingException;
import org.example.model.User;


import java.util.List;

public interface SiteService {
    List<User> getUserList();

    String addUser(User user) throws JsonProcessingException;

    String changeUser(User user);

    String deleteUser(Long id);

    String getCookie();

}
