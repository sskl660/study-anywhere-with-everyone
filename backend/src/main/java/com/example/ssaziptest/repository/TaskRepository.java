package com.example.ssaziptest.repository;

import com.example.ssaziptest.domain.task.TaskEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface TaskRepository extends JpaRepository<TaskEntity,Integer> {
    List<TaskEntity> findByTaskChallengeEntity_ChallengeNo(@Param(value = "challengeno") int challengeno);
}
