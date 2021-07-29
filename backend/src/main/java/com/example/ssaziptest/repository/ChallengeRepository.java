package com.example.ssaziptest.repository;

import com.example.ssaziptest.domain.challenge.ChallengeEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ChallengeRepository extends JpaRepository<ChallengeEntity, Integer> {
}
