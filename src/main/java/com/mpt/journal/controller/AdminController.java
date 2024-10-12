package com.mpt.journal.controller;

import com.mpt.journal.entity.UserEntity;
import com.mpt.journal.entity.RoleEnum;
import com.mpt.journal.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.Arrays;
import java.util.Set;

@Controller
@RequestMapping("/admin")
@PreAuthorize("hasAuthority('ADMIN')")
public class AdminController {

    @Autowired
    private UserRepository userRepository;

    @GetMapping("/users")
    public String userList(Model model) {
        model.addAttribute("users", userRepository.findAll());
        return "userList";
    }

    @GetMapping("/users/{id}")
    public String editUser(@PathVariable Long id, Model model) {
        UserEntity user = userRepository.findById(id).orElseThrow();
        model.addAttribute("user", user);
        model.addAttribute("roles", RoleEnum.values());
        return "userEdit";
    }

    @PostMapping("/users")
    public String saveUser(@RequestParam Long id,
                           @RequestParam String username,
                           @RequestParam Set<RoleEnum> roles) {
        UserEntity user = userRepository.findById(id).orElseThrow();
        user.setUsername(username);
        user.setRoles(roles);
        userRepository.save(user);
        return "redirect:/admin/users";
    }
}
