package com.example.ssaziptest.controller;

import com.example.ssaziptest.domain.task.LikeRequest;
import com.example.ssaziptest.domain.task.TaskLikeCheckResponse;
import com.example.ssaziptest.domain.task.TaskEntity;
import com.example.ssaziptest.domain.task.TaskSubmitRequest;
import com.example.ssaziptest.domain.task.TaskUpdateRequest;
import com.example.ssaziptest.repository.ChallengeRepository;
import com.example.ssaziptest.repository.TaskRepository;
import com.example.ssaziptest.repository.UserRepository;
import com.example.ssaziptest.service.TaskService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.sql.rowset.serial.SerialBlob;
import java.sql.Blob;
import java.util.Arrays;


@Api(tags = {"5.Task"})
@RestController
@RequestMapping(value = "/challenge/task")
public class TaskController {
    private static final Logger LOGGER = LoggerFactory.getLogger(TaskController.class);

    @Autowired
    private TaskService taskService;
    @Autowired
    private TaskRepository taskRepository;
    @Autowired
    private UserRepository userRepository;
    @Autowired
    private ChallengeRepository challengeRepository;

    /*과제 제출*/
    @ApiOperation(value = "과제 제출")
    @PostMapping(value = "", consumes = {MediaType.MULTIPART_FORM_DATA_VALUE})
    public void submitTask(@ModelAttribute("request") TaskSubmitRequest request, MultipartFile img, MultipartFile file) throws Exception{

        String filename = file.getOriginalFilename();

//        System.out.println(filename);
//        System.out.println(file.getContentType());

        TaskEntity taskEntity = TaskEntity.builder()
                .taskUserEntity(userRepository.getById(request.getUserEmail()))
                .taskChallengeEntity(challengeRepository.getById(request.getChallengeNo()))
                .taskFileName(filename)
                .taskFileType(file.getContentType())
                .taskIndex(request.getTaskIndex())
                .taskContent(request.getTaskContent())
                .taskDesc(request.getTaskDesc())
                .build();
        if(img!=null){
            byte[] contents = img.getBytes();
            Blob imgblob = new SerialBlob(contents);
            taskEntity.setTaskImage(imgblob);
        }else System.out.println("img가 null이래");
        if(file!=null){
            byte[] contents = file.getBytes();
            Blob fileblob = new SerialBlob(contents);
            taskEntity.setTaskFile(fileblob);
        }

        taskRepository.save(taskEntity);
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

    /*과제 이미지 조회*/
    @GetMapping(value = "img/{taskno}")
    public String getTaskByteImg(@PathVariable(name = "taskno") int taskno) throws Exception{
        //Blob blob = fileRepository.getById(fileno).getFileData();
        Blob blob = taskRepository.getById(taskno).getTaskImage();
        int bloblength = (int)blob.length();
        byte[] blobAsBytes = blob.getBytes(1,bloblength);
        blob.free();
        return Arrays.toString(blobAsBytes);
    }
    /*과제 파일 조회*/
    @GetMapping(value = "file/{taskno}")
    public byte[] getTaskByteFile(@PathVariable(name = "taskno") int taskno) throws Exception{
        //Blob blob = fileRepository.getById(fileno).getFileData();
        Blob blob = taskRepository.getById(taskno).getTaskFile();
        int bloblength = (int)blob.length();
        byte[] blobAsBytes = blob.getBytes(1,bloblength);
        blob.free();
        return blobAsBytes;
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

    /*좋아요 조회*/
    @ApiOperation(value = "좋아요 여부")
    @GetMapping(value = "/like/{user_email}/{task_no}")
    public ResponseEntity<TaskLikeCheckResponse> likeCheck(@PathVariable("user_email") String user_email, @PathVariable("task_no") int task_no)throws Exception{
        LOGGER.info(user_email+ " "+ task_no);
        TaskLikeCheckResponse taskLikeCheckResponse=taskService.likeCheck(user_email,task_no);
        return ResponseEntity.ok(taskLikeCheckResponse);
    }

}
