package com.example.ssaziptest.controller;

import com.example.ssaziptest.domain.task.TaskSubmitRequest;
import com.example.ssaziptest.domain.task.TaskUpdateRequest;
import com.example.ssaziptest.service.TaskService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(value = "/challenge/task")
public class TaskController {
    @Autowired
    private TaskService taskService;

    /*과제 제출*/
    @PostMapping(value = "")
    public void submitTask(@RequestBody TaskSubmitRequest request){
        taskService.submitTask(request);
    }

    /*과제 수정*/
    @PutMapping(value = "")
    public void updateTask(@RequestBody TaskUpdateRequest request){
        taskService.updateTask(request);
    }

    /*과제 삭제*/
    @DeleteMapping(value = "/{taskno}")
    public void deleteTask(@PathVariable("taskno") int taskno){
        taskService.deleteTask(taskno);
    }
}
