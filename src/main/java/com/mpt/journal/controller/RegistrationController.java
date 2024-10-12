package com.mpt.journal.controller;

import com.mpt.journal.entity.RoleEnum;
import com.mpt.journal.entity.UserEntity;
import com.mpt.journal.repository.UserRepository;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import java.util.Collections;

@Controller
public class RegistrationController {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @GetMapping("/registration")
    public String showRegistrationForm(Model model) {
        model.addAttribute("user", new UserEntity());
        return "registration";
    }

    @PostMapping("/registration")
    public String registerUser(@Valid @ModelAttribute("user") UserEntity user,
                               BindingResult bindingResult,
                               Model model,
                               @RequestParam("role") String role) {
        if (bindingResult.hasErrors()) {
            return "registration";
        }
        if (userRepository.existsByUsername(user.getUsername())) {
            model.addAttribute("message", "Пользователь с таким именем уже существует");
            return "registration";
        }
        user.setActive(true);

        if (role.equals("USER")) {
            user.setRoles(Collections.singleton(RoleEnum.USER));
        } else if (role.equals("ADMIN")) {
            user.setRoles(Collections.singleton(RoleEnum.ADMIN));
        } else {
            user.setRoles(Collections.singleton(RoleEnum.USER));
        }
        user.setPassword(passwordEncoder.encode(user.getPassword()));
        userRepository.save(user);
        return "redirect:/login";
    }
}
