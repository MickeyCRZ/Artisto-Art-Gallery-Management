package com.luminar.artisto.controller;


import com.luminar.artisto.model.ArtistModel;
import com.luminar.artisto.model.UserModel;
import com.luminar.artisto.repository.UserRepository;
import com.luminar.artisto.service.UserService;
import jakarta.validation.Valid;
import org.apache.catalina.User;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;

@RequestMapping("/register")
@Controller
public class RegistrationController {

    private UserService userService;

    public RegistrationController(UserService userService) {
        this.userService = userService;
    }
    @GetMapping("/user")
    public String getUserRegistrationPage(Model model) {
        model.addAttribute("user",new UserModel());


        return "user_registration";
    }

    @GetMapping("/artist")
    public String getArtistRegistrationPage(Model model) {
        model.addAttribute("artist",new ArtistModel());


        return "artist_registration";
    }

    @PostMapping("/user")
    public String registerUser(@Valid @ModelAttribute("user") UserModel userModel, BindingResult result, @RequestParam("file")MultipartFile file, Model model) throws IOException {

        if(result.hasErrors()) {
            return "user_registration";
        }
        UserModel registeredUser=userService.registerUser(userModel.getFirstName(),userModel.getLastName(),userModel.getUserEmail(),userModel.getUserPassword(),userModel.getUserName(),file);
        model.addAttribute("user",registeredUser);
        return "login_page";
    }


    @PostMapping("/artist")
    public String registerArtist(@Valid @ModelAttribute("artist") ArtistModel artistModel, BindingResult result,Model model,@RequestParam("file")MultipartFile file) throws IOException {

        if(result.hasErrors()) {
            return "artist_registration";
        }
        ArtistModel registeredArtist=userService.registerArtist(artistModel.getArtistName(),artistModel.getArtistLogin(),artistModel.getArtistEmail(),artistModel.getArtistPassword(),file);
        model.addAttribute("artist",registeredArtist);
        return "login_page";
    }





}
