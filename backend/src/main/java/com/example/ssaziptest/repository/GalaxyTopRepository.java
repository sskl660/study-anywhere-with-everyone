package com.example.ssaziptest.repository;

import com.example.ssaziptest.domain.rank.GalaxyTopEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface GalaxyTopRepository extends JpaRepository<GalaxyTopEntity, String> {
}
