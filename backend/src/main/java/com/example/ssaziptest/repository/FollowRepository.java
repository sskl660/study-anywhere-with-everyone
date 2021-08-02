package com.example.ssaziptest.repository;

import com.example.ssaziptest.domain.follow.FollowEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface FollowRepository extends JpaRepository<FollowEntity, Integer> {
    List<FollowEntity> findByFollowUserEntity_UserEmail(@Param(value = "useremail") String useremail);
    List<FollowEntity> findByFollowFollower(String followfollower);
    FollowEntity findByFollowUserEntity_UserEmailAndFollowFollower(@Param(value = "useremail") String useremail,String followfollower);
}
