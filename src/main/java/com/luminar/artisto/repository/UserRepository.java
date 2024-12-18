package com.luminar.artisto.repository;

import com.luminar.artisto.model.UserModel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepository extends JpaRepository<UserModel, Long> {
    UserModel findByUserNameAndUserPassword(String userName, String userPassword);
}
