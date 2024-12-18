package com.luminar.artisto.controller;


import com.luminar.artisto.model.ArtistModel;
import com.luminar.artisto.model.ArtworkModel;
import com.luminar.artisto.repository.ArtworkRepository;
import com.luminar.artisto.service.ArtworkService;
import com.luminar.artisto.service.UserService;
import jakarta.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

@Controller
public class ArtistController {

    @Autowired
    private ArtworkService artworkService;

    @Autowired
    private UserService userService;

    @Autowired
    private ArtworkRepository artworkRepository;
    @GetMapping("/addProduct")
    public String addProduct(Model model) {
        model.addAttribute("artwork",new ArtworkModel());

        return "add_product.html";
    }

    @PostMapping("/addProduct")
    public String saveArtwork(@ModelAttribute("artwork") ArtworkModel artwork,
                              @RequestParam("file") MultipartFile file, HttpSession session) {
        try {
            if (!file.isEmpty()) {

                ArtistModel artistObject = (ArtistModel) session.getAttribute("artistObject");
                String artistName=artistObject.getArtistName();
                String artistLogin=artistObject.getArtistLogin();
                String s=artworkService.addNewProduct(artwork.getArtName(),artwork.getPrice(),artwork.getDescription(),file,artistName,artistLogin);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

        return "redirect:/addProduct?success";
    }


    @GetMapping("/viewListed")
    public String viewArtwork(Model model) {
        List<ArtworkModel> artworkModelList=artworkRepository.findAll();

        model.addAttribute("artworks",artworkModelList);
        return "view_listed_products";

    }

    @GetMapping("/artwork/image/{id}")
    public ResponseEntity<byte[]> getArtworkImage(@PathVariable Long id) {

       return artworkRepository.findById(id).map(artwork->ResponseEntity.ok().header(HttpHeaders.CONTENT_DISPOSITION,"inline;filename=\"artwork-image.jpg\"").contentType(MediaType.IMAGE_PNG).body(artwork.getImageFile())).orElse(ResponseEntity.notFound().build());
    }

    @GetMapping("/editArtist")
    public String editArtistDetails(HttpSession session,Model model) {
        ArtistModel artist=(ArtistModel)session.getAttribute("artistObject");
        model.addAttribute("artist",artist);

        return "edit_artist_page";
    }

    @PostMapping("/update_artist_details")
    public String updateArtistDetails(@ModelAttribute("artist") ArtistModel updatedArtist) {
        // Update the user details in the database


        userService.updateArtist(updatedArtist);
        System.out.println("SUCCESSFULLY UPDATED!");

        return "artist_dashboard";
        }
}

