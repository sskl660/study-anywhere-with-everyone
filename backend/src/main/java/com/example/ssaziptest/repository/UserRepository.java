package com.example.ssaziptest.repository;

import com.example.ssaziptest.domain.user.UserEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface UserRepository extends JpaRepository<UserEntity, String> {
    Optional<UserEntity> findUserEntityByUserEmailAndUserPassword(String userEmail, String userPasswordㅣ);
    List<UserEntity> findTop5ByOrderByUserWeekcomplete();
    List<UserEntity> findTop5ByOrderByUserWeektime();

//    @Query("select user_email, user_name from user order by RAND()")
//    List<UserEntity> findRandomGraduates();
}
