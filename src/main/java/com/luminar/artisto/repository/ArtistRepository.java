package com.luminar.artisto.repository;

import com.luminar.artisto.model.ArtistModel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ArtistRepository extends JpaRepository<ArtistModel,Long> {
    ArtistModel findByArtistLoginAndArtistPassword(String userName, String userPassword);
}
