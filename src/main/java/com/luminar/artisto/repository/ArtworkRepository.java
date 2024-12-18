package com.luminar.artisto.repository;

import com.luminar.artisto.model.ArtworkModel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ArtworkRepository extends JpaRepository<ArtworkModel, Long> {
}