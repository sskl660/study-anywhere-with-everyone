package com.example.ssaziptest.repository;

import com.example.ssaziptest.domain.rank.ChallengeTopEntity;
import com.example.ssaziptest.domain.rank.GalaxyTopEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ChallengeTopRepository extends JpaRepository<ChallengeTopEntity, String> {
}
