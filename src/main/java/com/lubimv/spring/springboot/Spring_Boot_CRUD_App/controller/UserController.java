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
        if (bindingResult.hasErrors()) {
            return "users/new";
        }
        userService.addUser(user);

        return "redirect:/users";
    }

    @GetMapping("/{email}/edit")
    public String editUser(Model model, @PathVariable("email") String email) {
        model.addAttribute("user", userService.getUser(email));
        return "users/edit";
    }

    @PatchMapping("/{email}")
    public String updateUser(@ModelAttribute("user") @Valid User user, BindingResult bindingResult,
                             @PathVariable("email") String email) {
        if (bindingResult.hasErrors()) {
            return "users/edit";
        }
        userService.updateUser(email, user);
        return "redirect:/users";
    }

    @DeleteMapping("/{email}")
    public String delete(@PathVariable("email") String email) {
        userService.deleteUser(email);
        return "redirect:/users";
    }
}
