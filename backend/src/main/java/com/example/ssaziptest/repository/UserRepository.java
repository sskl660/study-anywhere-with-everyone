package com.example.ssaziptest.repository;

import com.example.ssaziptest.domain.user.UserEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UserRepository extends JpaRepository<UserEntity, String> {
    Optional<UserEntity> findUserEntityByUserEmailAndUserPassword(String userEmail, String userPasswordㅣ);

}
