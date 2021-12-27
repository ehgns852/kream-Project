package com.nklcb.kream.controller;

import com.nklcb.kream.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api")
@Slf4j
public class LoginApiController {

    private final UserRepository userRepository;



    @GetMapping("/loginForm")
    public String loginForm() {
      log.info("loginform");
        return "loginForm";
    }


}
