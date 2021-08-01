package com.example.ssaziptest.repository;

import com.example.ssaziptest.domain.group.GroupmemberEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface GroupmemberRepository extends JpaRepository<GroupmemberEntity, Integer> {
}
