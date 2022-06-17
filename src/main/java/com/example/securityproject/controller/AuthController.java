package com.example.securityproject.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/auth/")
public class AuthController {

    @GetMapping("login")
    public String getLogin(){
        return "login.jsp";
    }


    @GetMapping("success")
    public String getS(){
        return "success.jsp";
    }
}
