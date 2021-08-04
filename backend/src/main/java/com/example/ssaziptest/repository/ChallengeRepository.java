package com.example.ssaziptest.repository;

import com.example.ssaziptest.domain.challenge.ChallengeEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ChallengeRepository extends JpaRepository<ChallengeEntity, Integer> {
    List<ChallengeEntity> findByChallengeName(String keyword);
}
