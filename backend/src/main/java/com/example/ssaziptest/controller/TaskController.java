package com.example.ssaziptest.controller;

import com.example.ssaziptest.domain.task.LikeRequest;
import com.example.ssaziptest.domain.task.TaskSubmitRequest;
import com.example.ssaziptest.domain.task.TaskUpdateRequest;
import com.example.ssaziptest.service.TaskService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@Api(tags = {"5.Task"})
@RestController
@RequestMapping(value = "/challenge/task")
public class TaskController {
    @Autowired
    private TaskService taskService;

    /*과제 제출*/
    @ApiOperation(value = "과제 제출")
    @PostMapping(value = "")
    public void submitTask(@RequestBody TaskSubmitRequest request){
        taskService.submitTask(request);
    }

    /*과제 수정*/
    @ApiOperation(value = "과제 수정")
    @PutMapping(value = "")
    public void updateTask(@RequestBody TaskUpdateRequest request){
        taskService.updateTask(request);
    }

    /*과제 삭제*/
    @ApiOperation(value = "과제 삭제")
    @DeleteMapping(value = "/{taskno}")
    public void deleteTask(@PathVariable("taskno") int taskno){
        taskService.deleteTask(taskno);
    }

    /*좋아요*/
    @ApiOperation(value = "좋아요")
    @PostMapping(value = "/like")
    public void like(@RequestBody LikeRequest request){
        taskService.like(request);
    }

    /*좋아요 취소*/
    @ApiOperation(value = "좋아요 취소")
    @DeleteMapping(value = "/unlike")
    public void unlike(@RequestBody LikeRequest request){
        taskService.unlike(request);
    }
}
