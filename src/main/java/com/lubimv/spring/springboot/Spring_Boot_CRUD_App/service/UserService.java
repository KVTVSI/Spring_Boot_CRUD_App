package com.lubimv.spring.springboot.Spring_Boot_CRUD_App.service;



import com.lubimv.spring.springboot.Spring_Boot_CRUD_App.model.User;

import java.util.List;

public interface UserService {

    void addUser(User user);

    User getUser(Long id);

    List<User> getAllUsers();

    void updateUser(User updatedUser);

    void deleteUser(Long id);
}
