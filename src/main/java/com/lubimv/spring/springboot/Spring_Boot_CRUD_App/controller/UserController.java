package com.lubimv.spring.springboot.Spring_Boot_CRUD_App.controller;

import com.lubimv.spring.springboot.Spring_Boot_CRUD_App.model.User;
import com.lubimv.spring.springboot.Spring_Boot_CRUD_App.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RequestMapping("/users")
@Controller
public class UserController {

    private final UserService userService;

    @Autowired
    public UserController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping()
    public String getAllUsers(Model model) {
        model.addAttribute("users", userService.getAllUsers());
        return "users/userslist";
    }

    @GetMapping("/new")
    public String addNewUser(Model model) {
        model.addAttribute("user", new User());

        return "users/new";
    }

    @PostMapping
    public String saveUser(@ModelAttribute("user") @Valid User user,
                           BindingResult bindingResult) {
        try {
            if (bindingResult.hasErrors()) {
                return "users/new";
            } else {
                userService.addUser(user);
            }
        } catch (Exception e) {
            bindingResult.rejectValue("email", "error.user", "Пользователь с таким email уже существует");
            return "users/new";
        }
        return "redirect:/users";
    }

    @GetMapping("/{id}/edit")
    public String editUser(Model model, @PathVariable("id") Long id) {
        model.addAttribute("user", userService.getUser(id));
        return "users/edit";
    }

    @PatchMapping("/{id}")
    public String updateUser(@ModelAttribute("user") @Valid User user, BindingResult bindingResult) {
        try {
            if (bindingResult.hasErrors()) {
                return "users/edit";
            } else {
                userService.updateUser(user);
            }
        } catch (Exception e) {
            bindingResult.rejectValue("email", "error.user", "Пользователь с таким email уже существует");
            return "users/edit";
        }

        return "redirect:/users";
    }

    @DeleteMapping("/{id}")
    public String delete(@PathVariable("id") Long id) {
        userService.deleteUser(id);
        return "redirect:/users";
    }
}
