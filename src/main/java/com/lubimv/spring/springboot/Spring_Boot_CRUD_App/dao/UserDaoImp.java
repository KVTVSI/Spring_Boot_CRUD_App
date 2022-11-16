package com.lubimv.spring.springboot.Spring_Boot_CRUD_App.dao;

import com.lubimv.spring.springboot.Spring_Boot_CRUD_App.model.User;
import org.apache.commons.validator.EmailValidator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;


import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.List;

@Repository
public class UserDaoImp implements UserDao {
    @PersistenceContext
    private EntityManager entityManager;

    final EmailValidator emailValidator;

    @Autowired
    public UserDaoImp(EmailValidator emailValidator) {
        this.emailValidator = emailValidator;
    }

    public boolean existsUser(String email) {
        return entityManager.createQuery("from User where email = :email", User.class)
                .setParameter("email", email)
                .getResultList()
                .stream()
                .findAny()
                .orElse(null) != null;
    }

    @Override
    public boolean addUser(User user) {
        if (!existsUser(user.getEmail())) {
            entityManager.persist(user);
            return true;
        } else {
            return false;
        }
    }

    @Override
    public User getUser(String email) {
        return entityManager.createQuery("from User where email = :email", User.class)
                .setParameter("email", email)
                .getSingleResult();
    }

    @Override
    public List<User> getAllUsers() {
        return entityManager.createQuery("from User").getResultList();
    }

    @Override
    public void updateUser(String userEmail, User updatedUser) {
        if (!existsUser(updatedUser.getEmail())|| userEmail.equals(updatedUser.getEmail())) {
            User userToBeUpdated = getUser(userEmail);

            userToBeUpdated.setEmail(updatedUser.getEmail());
            userToBeUpdated.setName(updatedUser.getName());
            userToBeUpdated.setLastName(updatedUser.getLastName());
        }
    }


    @Override
    public boolean deleteUser(String email) {
        if (existsUser(email)) {
            entityManager.remove(getUser(email));
            return true;
        } else {
            return false;
        }
    }


}