package com.luminar.artisto.service;


import com.luminar.artisto.model.ArtworkModel;
import com.luminar.artisto.repository.ArtworkRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;

@Service
public class ArtworkService {


    @Autowired
    private ArtworkRepository artworkRepository;



    public ArtworkService(ArtworkRepository artworkRepository) {
        this.artworkRepository = artworkRepository;
    }

    public String addNewProduct(String artName, float price, String description,MultipartFile file,String artistName,String artistLogin) throws IOException {
        ArtworkModel artworkModel=new ArtworkModel();
        artworkModel.setArtName(artName);
        artworkModel.setPrice(price);
        artworkModel.setDescription(description);
        artworkModel.setCreatedBy(artistName);
        artworkModel.setArtistLogin(artistLogin);

        artworkModel.setImageFile(file.getBytes());

        artworkRepository.save(artworkModel);

        if(artworkModel!=null) {
            return "SUCCESSFULLY ADDED PRODUCT";
        }
        return "ERROR ADDING PRODUCT";
    }


}
