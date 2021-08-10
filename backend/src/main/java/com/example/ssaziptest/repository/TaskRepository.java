package com.example.ssaziptest.repository;

import com.example.ssaziptest.domain.task.TaskEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import javax.transaction.Transactional;
import java.util.List;

@Repository
public interface TaskRepository extends JpaRepository<TaskEntity,Integer> {

    @Modifying
    @Transactional
    void deleteById(int taskno);

    List<TaskEntity> findByTaskChallengeEntity_ChallengeNo(@Param(value = "challengeno") int challengeno);
    List<TaskEntity> findByTaskChallengeEntity_ChallengeNoAndTaskUserEntity_UserEmail(@Param(value = "challengeno")int challengeno, @Param(value = "useremail")String useremail);
}
