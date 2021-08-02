package com.example.ssaziptest.controller;

import com.example.ssaziptest.domain.challenge.ChallengeDetailResponse;
import com.example.ssaziptest.domain.group.ChallengeJoinRequest;
import com.example.ssaziptest.domain.task.BulletJournalResponse;
import com.example.ssaziptest.domain.task.TaskDetailResponse;
import com.example.ssaziptest.service.ChallengeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(value = "/challenge")
public class ChallengeController {

    @Autowired
    private ChallengeService challengeService;

    /*챌린지 정보 요청*/
    @GetMapping(value = "/info/{challengeno}")
    public ResponseEntity<ChallengeDetailResponse> getChallengeDetail(@PathVariable("challengeno") int challengeno) throws Exception{
        ChallengeDetailResponse challengeDetailResponse = challengeService.getChallengeDetail(challengeno);
        if(challengeDetailResponse==null) new ResponseEntity<>(null, HttpStatus.NO_CONTENT);
        return new ResponseEntity<>(challengeDetailResponse,HttpStatus.OK);
    }

    /*챌린지 가입 요청*/
    @PostMapping(value = "/join")
    public void joinChallenge(@RequestBody ChallengeJoinRequest request){
        challengeService.joinChallenge(request.getUserEmail(), request.getChallengeNo());
    }

    /*Bullet Journal 과제 현황 요청*/
    @GetMapping(value = "/tasklist/{challengeno}")
    public ResponseEntity<List<BulletJournalResponse>> taskList(@PathVariable("challengeno") int challengeNo) throws Exception{
        List<BulletJournalResponse> bulletJournalResponses = challengeService.getTaskList(challengeNo);
        if(bulletJournalResponses.isEmpty()) return new ResponseEntity<>(null,HttpStatus.NO_CONTENT);
        return new ResponseEntity<>(bulletJournalResponses,HttpStatus.OK);
    }

    /*Bullet Journal 과제 상세 열기*/
    @GetMapping(value = "/task/{taskno}")
    public ResponseEntity<TaskDetailResponse> taskDetail(@PathVariable("taskno") int taskNo) throws Exception{
        TaskDetailResponse taskDetailResponse = challengeService.getTaskDetail(taskNo);
        return new ResponseEntity<>(taskDetailResponse,HttpStatus.OK);
    }

}
