package com.luminar.artisto.controller;


import com.luminar.artisto.model.UserModel;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;



@Controller
public class IndexController {

    @RequestMapping("/")
    public String getIndex() {

        return "index";
    }


    @GetMapping("/loginChoice")
    public String login() {

        return "login_page";
    }

    @GetMapping("/registerChoice")
    public String register() {

        return "registration_page";
    }
}
