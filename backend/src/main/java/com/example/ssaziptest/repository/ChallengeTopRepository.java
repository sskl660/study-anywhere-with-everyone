package com.example.ssaziptest.repository;

import com.example.ssaziptest.domain.rank.ChallengeTopEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ChallengeTopRepository extends JpaRepository<ChallengeTopEntity, String> {
}
