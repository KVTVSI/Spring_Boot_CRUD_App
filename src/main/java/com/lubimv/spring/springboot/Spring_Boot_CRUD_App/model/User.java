package com.lubimv.spring.springboot.Spring_Boot_CRUD_App.model;

import javax.persistence.*;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Size;

@Entity
@Table(name = "users")
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(name = "name")
    @NotEmpty(message = "Поле Name не может быть пустым")
    @Size(min = 2, max = 30, message = "Поле Name должно содержать не меньше 2х и не больше 30 символов")
    private String name;
    @Column(name = "lastname")
    @NotEmpty(message = "Поле Last Name не может быть пустым")
    @Size(min = 2, max = 30, message = "Поле Last Name должно содержать не меньше 2х и не больше 30 символов")
    private String lastName;
    @Column (name = "email")
    @NotEmpty(message = "Поле Email не может быть пустым")
    @Email(message = "Не правильно введен Email")
    private String email;

    public User() {
    }

    public User(String name, String lastName, String email) {
        this.name = name;
        this.lastName = lastName;
        this.email = email;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    @Override
    public String toString() {
        return "User{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", lastName='" + lastName + '\'' +
                ", email='" + email + '\'' +
                '}';
    }
}
