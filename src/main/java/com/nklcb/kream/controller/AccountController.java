package com.nklcb.kream.controller;

import com.nklcb.kream.entity.security.User;
import com.nklcb.kream.repository.RoleRepository;
import com.nklcb.kream.repository.UserRoleRepository;
import com.nklcb.kream.service.UserService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/account")
@RequiredArgsConstructor
@Slf4j
public class AccountController {

    private final UserService userService;
    private final UserRoleRepository userRoleRepository;
    private final RoleRepository roleRepository;

    @GetMapping("/login")
    public String login() {
        log.info("login AccountController");
        return "account/login";
    }


    @GetMapping("/register")
    public String getRegister(@ModelAttribute(name = "user") User user){
        return "account/register";
    }

    @PostMapping("/register")
    public String register(@ModelAttribute(name = "user") User user) {

        log.info("in account Controller");

        userService.userJoin(user);

        log.info("success userService.save");
        return "redirect:/";
    }
}
