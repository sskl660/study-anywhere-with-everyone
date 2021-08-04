package com.example.ssaziptest.service;

import com.example.ssaziptest.domain.comment.CommentEntity;
import com.example.ssaziptest.domain.comment.CommentListResponse;
import com.example.ssaziptest.domain.comment.CommentRequest;
import com.example.ssaziptest.repository.CommentRepository;
import com.example.ssaziptest.repository.TaskRepository;
import com.example.ssaziptest.repository.UserRepository;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.ArrayList;
import java.util.List;

@Service
@NoArgsConstructor
@AllArgsConstructor
public class CommentService {

    @Autowired
    private CommentRepository commentRepository;
    @Autowired
    private TaskRepository taskRepository;
    @Autowired
    private UserRepository userRepository;

    @Transactional
    public List<CommentListResponse> getCommentList(int taskNo){
        Sort sort = Sort.by(Sort.Direction.ASC, "commentDate");
        List<CommentEntity> commentEntities = commentRepository.findByCommentTaskEntity_TaskNo(taskNo,sort);
        List<CommentListResponse> list = new ArrayList<>();
        for(CommentEntity commentEntity: commentEntities){
            CommentListResponse response = CommentListResponse.builder()
                    .userEmail(commentEntity.getCommentUserEntity().getUserEmail())
                    .userName(commentEntity.getCommentUserEntity().getUserName())
                    .userImage(commentEntity.getCommentUserEntity().getUserImage())
                    .commentContent(commentEntity.getCommentContent())
                    .commentDate(commentEntity.getCommentDate())
                    .build();
            list.add(response);
        }
        return list;
    }

    @Transactional
    public List<CommentListResponse> createComment(CommentRequest request){
        CommentEntity commentEntity = CommentEntity.builder()
                .commentTaskEntity(taskRepository.getById(request.getTaskNo()))
                .commentUserEntity(userRepository.getById(request.getUserEmail()))
                .commentContent(request.getCommentContent())
                .build();
        commentRepository.save(commentEntity);
        return getCommentList(commentEntity.getCommentTaskEntity().getTaskNo());
    }
}
