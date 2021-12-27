package com.nklcb.kream.controller;

import com.nklcb.kream.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
public class LoginApiController {

    private final UserRepository userRepository;


}
