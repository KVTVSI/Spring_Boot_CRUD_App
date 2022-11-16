package com.lubimv.spring.springboot.Spring_Boot_CRUD_App.dao;



import com.lubimv.spring.springboot.Spring_Boot_CRUD_App.model.User;

import java.util.List;

public interface UserDao {

    boolean addUser(User user);

    User getUser(String email);

    List<User> getAllUsers();

    void updateUser(String userEmail, User updatedUser);

    boolean deleteUser(String email);


}
