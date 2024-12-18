package com.luminar.artisto.controller;

import com.luminar.artisto.model.ArtistModel;
import com.luminar.artisto.model.ArtworkModel;
import com.luminar.artisto.model.UserModel;
import com.luminar.artisto.repository.ArtworkRepository;
import com.luminar.artisto.service.UserService;
import jakarta.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;


@RequestMapping("/login")
@Controller
public class LoginController {
    public LoginController(UserService userService) {
        this.userService = userService;
    }
    @Autowired
    UserService userService;
    @Autowired
    ArtworkRepository artworkRepository;

    @GetMapping("/user")
    public String getUserLoginPage() {



        return "user_login_page";
    }

    @GetMapping("/artist")
    public String getArtistLoginPage() {



        return "artist_login_page";
    }
    @PostMapping("/user")
    public String getUserHome(@RequestParam String userName, @RequestParam String userPassword, HttpSession session, Model model) {

        UserModel userModel= userService.authenticateUser(userName,userPassword);
        if(userModel!=null){
            session.setAttribute("userObject",userModel);
            model.addAttribute("user",userModel);
            List<ArtworkModel> artworkModelList=artworkRepository.findAll();

            model.addAttribute("artworks",artworkModelList);

            return "user_dashboard";
        }
        System.out.print("LOGIN FAILED! TRY AGAIN");
        return "redirect:user_login_page";
    }

    @PostMapping("/artist")
    public String getArtistHome(@RequestParam String userName, @RequestParam String userPassword, HttpSession session) {

        ArtistModel artistModel= userService.authenticateArtist(userName,userPassword);
        if(artistModel!=null){
            session.setAttribute("artistObject",artistModel);
            return "artist_dashboard";
        }
        System.out.print("LOGIN FAILED! TRY AGAIN");
        return "redirect:artist_login_page";
    }
}
