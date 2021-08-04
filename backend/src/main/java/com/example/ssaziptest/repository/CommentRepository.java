package com.example.ssaziptest.repository;

import com.example.ssaziptest.domain.comment.CommentEntity;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CommentRepository extends JpaRepository<CommentEntity, Integer> {
    List<CommentEntity> findByCommentTaskEntity_TaskNo(int taskNo, Sort sort);

}
