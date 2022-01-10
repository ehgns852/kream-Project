package com.nklcb.kream.controller;

import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@Slf4j
@Controller
@RequestMapping("/admin")
public class AdminController {

    @GetMapping("/productManagement")
    public String adminProductManagement(){

        log.info("in AdminController");

        return "item/itemList";
    }
}
