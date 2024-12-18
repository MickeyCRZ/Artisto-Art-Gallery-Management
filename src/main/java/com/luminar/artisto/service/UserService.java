package com.luminar.artisto.service;

import com.luminar.artisto.model.ArtistModel;
import com.luminar.artisto.model.UserModel;
import com.luminar.artisto.repository.ArtistRepository;
import com.luminar.artisto.repository.UserRepository;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.Optional;


@Service
public class UserService {



    public UserService(UserRepository userRepository,ArtistRepository artistRepository) {
        this.userRepository = userRepository;
        this.artistRepository = artistRepository;
    }
    @Autowired
    ArtistRepository artistRepository;
    @Autowired
    UserRepository userRepository;

    public UserModel registerUser(String firstName, String lastName, String email, String password, String login, MultipartFile file) throws IOException {

        UserModel userModel=new UserModel();
        userModel.setFirstName(firstName);
        userModel.setLastName(lastName);
        userModel.setUserEmail(email);
        userModel.setUserPassword(password);
        userModel.setUserName(login);
        userModel.setImageFile(file.getBytes());
        try {
            userRepository.save(userModel);
        }catch(Exception e){
            System.out.println("ERROR CREATING USER...USER MIGHT EXIST");
        }
        return userModel;
    }

    public ArtistModel registerArtist(String artistName, String artistLogin, String artistEmail, String artistPassword, MultipartFile file) throws IOException {

        ArtistModel artistModel=new ArtistModel();
        artistModel.setArtistName(artistName);
        artistModel.setArtistLogin(artistLogin);
        artistModel.setArtistEmail(artistEmail);
        artistModel.setArtistPassword(artistPassword);
        artistModel.setImageFile(file.getBytes());

        try {
            artistRepository.save(artistModel);
        } catch (Exception e) {
            System.out.println("Username already exists!");
        }

        return artistModel;
    }

    public UserModel authenticateUser(String userName,String userPassword) {

        return userRepository.findByUserNameAndUserPassword(userName,userPassword);
    }

    public ArtistModel authenticateArtist(String userName,String userPassword) {

        return artistRepository.findByArtistLoginAndArtistPassword(userName,userPassword);
    }

    public void updateArtist(ArtistModel updatedArtist) {
        // Check for null or invalid artist input
        if (updatedArtist == null || updatedArtist.getId() == null) {
            throw new IllegalArgumentException("Artist details or ID cannot be null!");
        }

        // Fetch the existing artist details from the database
        Optional<ArtistModel> existingArtistOptional = artistRepository.findById(updatedArtist.getId());

        if (existingArtistOptional.isPresent()) {
            // Retrieve the existing artist
            ArtistModel existingArtist = existingArtistOptional.get();

            // Update fields only if they are not null or blank
            if (updatedArtist.getArtistName() != null && !updatedArtist.getArtistName().isBlank()) {
                existingArtist.setArtistName(updatedArtist.getArtistName());
            }
            if (updatedArtist.getArtistLogin() != null && !updatedArtist.getArtistLogin().isBlank()) {
                existingArtist.setArtistLogin(updatedArtist.getArtistLogin());
            }
            if (updatedArtist.getArtistPassword() != null && !updatedArtist.getArtistPassword().isBlank()) {
                existingArtist.setArtistPassword(updatedArtist.getArtistPassword());
            }
            if (updatedArtist.getArtistEmail() != null && !updatedArtist.getArtistEmail().isBlank()) {
                existingArtist.setArtistEmail(updatedArtist.getArtistEmail());
            }
            if (updatedArtist.getImageFile() != null) {
                existingArtist.setImageFile(updatedArtist.getImageFile());
            }

            // Save the updated artist details
            artistRepository.save(existingArtist);
            System.out.println("Artist with ID " + existingArtist.getId() + " successfully updated.");
        } else {
            throw new EntityNotFoundException("Artist not found with ID: " + updatedArtist.getId());
        }
    }

}
