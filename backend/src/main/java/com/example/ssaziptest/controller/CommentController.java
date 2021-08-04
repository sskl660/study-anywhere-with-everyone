package com.example.ssaziptest.controller;

import com.example.ssaziptest.domain.comment.CommentListResponse;
import com.example.ssaziptest.domain.comment.CommentRequest;
import com.example.ssaziptest.service.CommentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/comment")
public class CommentController {
    @Autowired
    private CommentService commentService;

    /*댓글 리스트*/
    @GetMapping(value = "/{taskno}")
    public ResponseEntity<List<CommentListResponse>> getComments(@PathVariable(name = "taskno")int taskNo){
        List<CommentListResponse> list = commentService.getCommentList(taskNo);
        return new ResponseEntity<>(list, HttpStatus.OK);
    }
    /*댓글 작성*/
    @PostMapping(value = "")
    public ResponseEntity<List<CommentListResponse>> createComment(@RequestBody CommentRequest request){
        List<CommentListResponse> list = commentService.createComment(request);
        return new ResponseEntity<>(list,HttpStatus.OK);
    }

}
