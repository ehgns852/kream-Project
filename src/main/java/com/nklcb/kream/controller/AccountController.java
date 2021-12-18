package com.nklcb.kream.controller;

import com.nklcb.kream.entity.security.User;
import com.nklcb.kream.service.UserService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
@RequestMapping("/account")
@RequiredArgsConstructor
@Slf4j
public class AccountController {

    private final UserService userService;

    @GetMapping("/login")
    public String login() {
        return "account/login";
    }

    @GetMapping("/register")
    public String getRegister(Model model, User user){
        model.addAttribute("user", user);
        return "account/register";
    }

    @PostMapping("/register")
    public String register(User user) {

        log.info("in account Controller");

        userService.save(user);
        log.info("success userservice.save");
        return "redirect:/";
    }
}
