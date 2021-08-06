package com.example.ssaziptest.repository;

import com.example.ssaziptest.domain.user.UserEntity;
import com.example.ssaziptest.domain.user.UserInfoResponse;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface UserRepository extends JpaRepository<UserEntity, String> {
    Optional<UserInfoResponse> findUserEntityByUserEmailAndUserPassword(String userEmail, String userPasswordㅣ);
    List<UserEntity> findTop5ByOrderByUserWeekcomplete();
    List<UserEntity> findTop5ByOrderByUserWeektime();

    @Query(nativeQuery = true, value = "select * from user where user_graduated = true order by rand() limit 3")
    List<UserEntity> findRandomGraduates();
}
