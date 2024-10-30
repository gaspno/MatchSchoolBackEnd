package com.mathschool.MathSchoolBack.endpoints;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class LoginPage {


    @GetMapping("login")
    public String login(){
        return "login";
    }
}
