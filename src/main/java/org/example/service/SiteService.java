package org.example.service;

import com.fasterxml.jackson.core.JsonProcessingException;
import org.example.model.User;

import java.util.List;

public interface SiteService {
    List<User> getUserList();

    void addUser(User user) throws JsonProcessingException;

    void changeUser(User user);

    void deleteUser(Long id);

    String getCookie();

}
