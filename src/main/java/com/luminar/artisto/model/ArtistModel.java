package com.luminar.artisto.model;


import jakarta.persistence.*;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.Data;
import org.springframework.web.multipart.MultipartFile;

@Entity
@Table(name = "artist")
@Data
public class ArtistModel {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @NotBlank(message = "Name is required")
    private String artistName;

    @Column(unique = true)
    @NotBlank(message = "Login is required")
    private String artistLogin;

    @NotBlank(message = "Password is required")
    @Size(min = 6, message = "Password must be at least 6 characters long")
    private String artistPassword;

    @Column(unique = true)
    @NotBlank(message = "Email is required")
    @Email(message = "Please provide a valid email")
    private String artistEmail;

    public byte[] getImageFile() {
        return imageFile;
    }

    public void setImageFile(byte[] imageFile) {
        this.imageFile = imageFile;
    }

    @Lob
    private byte[] imageFile;

    // Getters and Setters
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getArtistName() {
        return artistName;
    }

    public void setArtistName(String artistName) {
        this.artistName = artistName;
    }

    public String getArtistLogin() {
        return artistLogin;
    }

    public void setArtistLogin(String artistLogin) {
        this.artistLogin = artistLogin;
    }

    public String getArtistPassword() {
        return artistPassword;
    }

    public void setArtistPassword(String artistPassword) {
        this.artistPassword = artistPassword;
    }

    public String getArtistEmail() {
        return artistEmail;
    }

    public void setArtistEmail(String artistEmail) {
        this.artistEmail = artistEmail;
    }
}
