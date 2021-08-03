package com.example.ssaziptest.repository;

import com.example.ssaziptest.domain.feed.FeedEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface FeedRepository extends JpaRepository<FeedEntity,Integer> {
    List<FeedEntity> findByFeedOwner(String feedOwner);
}
