package com.example.ssaziptest.repository;

import com.example.ssaziptest.domain.group.GroupmemberEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface GroupmemberRepository extends JpaRepository<GroupmemberEntity, Integer> {
    List<GroupmemberEntity> findByGroupChallengeEntity_ChallengeNo(@Param(value = "challengeno") int challengeno);
    List<GroupmemberEntity> findByGroupUserEntity_UserEmail(@Param(value = "useremail")String useremail);
}
