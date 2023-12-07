package com.ra.controller;

import com.ra.model.entity.User;
import com.ra.model.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import java.util.List;

@Controller
public class HomeController {
    @Autowired
    private UserService userService;
    @GetMapping("/")
    public String demo(Model model){
        List<User> list = userService.findAll();
        model.addAttribute("list",list);
        return "home";
    }

    @GetMapping("/add-user")
    public String addUser(Model model) {
        User user = new User();
        model.addAttribute("user", user) ;
        return "add-user" ;
    }

    @PostMapping("/add-user")
    public String postAddUser(@ModelAttribute("user") User user) {
        userService.saveOrUpdate(user) ;
        return "redirect:/" ;
    }

    @GetMapping("/delete-user/{id}")
    public String deleteUser(@PathVariable("id") Integer id) {
        userService.delete(id) ;
        return "redirect:/" ;
    }

    @GetMapping("/edit-user/{id}")
    public String editUser(@PathVariable("id") Integer id, Model model) {
        User user = userService.findById(id) ;
        model.addAttribute(user) ;
        return "edit-user" ;
    }

    @PostMapping("/edit-user")
    public String postEditUser(@ModelAttribute("user") User user) {
        userService.saveOrUpdate(user) ;
        return "redirect:/" ;
    }
}
