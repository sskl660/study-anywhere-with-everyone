package com.example.ssaziptest.controller;

import com.example.ssaziptest.domain.challenge.ChallengeDetailResponse;
import com.example.ssaziptest.domain.group.ChallengeJoinRequest;
import com.example.ssaziptest.domain.task.BulletJournalResponse;
import com.example.ssaziptest.domain.task.ChallengeTicketResponse;
import com.example.ssaziptest.domain.task.TaskDetailResponse;
import com.example.ssaziptest.service.ChallengeService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Api(tags = {"2.Challenge"})
@RestController
@RequestMapping(value = "/challenge")
public class ChallengeController {

    @Autowired
    private ChallengeService challengeService;

    /*챌린지 정보 요청*/
    @ApiOperation(value = "챌린지 정보 요청", notes = "하나의 챌린지 상세 정보 요청")
    @ApiImplicitParam(name = "challengeno", value = "챌린지 pk 번호")
    @GetMapping(value = "/info/{challengeno}")
    public ResponseEntity<ChallengeDetailResponse> getChallengeDetail(@PathVariable("challengeno") int challengeno) throws Exception{
        ChallengeDetailResponse challengeDetailResponse = challengeService.getChallengeDetail(challengeno);
        if(challengeDetailResponse==null) new ResponseEntity<>(null, HttpStatus.NO_CONTENT);
        return new ResponseEntity<>(challengeDetailResponse,HttpStatus.OK);
    }

    /*챌린지 가입 요청*/
    @ApiOperation(value = "챌린지 가입 요청")
    @PostMapping(value = "/join")
    public void joinChallenge(@RequestBody ChallengeJoinRequest request){
        challengeService.joinChallenge(request.getUserEmail(), request.getChallengeNo());
    }

    /*Bullet Journal 과제 현황 요청*/
    @ApiOperation(value = "Bullet Journal 과제 현황 요청")
    @ApiImplicitParam(name = "challengeno", value = "챌린지 pk 번호")
    @GetMapping(value = "/tasklist/{challengeno}")
    public ResponseEntity<List<BulletJournalResponse>> taskList(@PathVariable("challengeno") int challengeNo) throws Exception{
        List<BulletJournalResponse> bulletJournalResponses = challengeService.getTaskList(challengeNo);
        if(bulletJournalResponses.isEmpty()) return new ResponseEntity<>(null,HttpStatus.NO_CONTENT);
        return new ResponseEntity<>(bulletJournalResponses,HttpStatus.OK);
    }
    /*챌린지 과제 티켓 현황 요청*/
    @ApiOperation(value = "챌린지 과제 티켓 현황 요청")
    @ApiImplicitParam(name = "challengeno", value = "챌린지 pk 번호")
    @GetMapping(value = "/taskticket/{challengeno}")
    public ResponseEntity<ChallengeTicketResponse[]> getChallengeTicket(@PathVariable("challengeno") int challengeNo) throws Exception{
        ChallengeTicketResponse[] challengeTicketResponses = challengeService.getChallengeTicket(challengeNo);
        return new ResponseEntity<>(challengeTicketResponses,HttpStatus.OK);
    }

    /*Bullet Journal 과제 상세 열기*/
    @ApiOperation(value = "Bullet Journal 과제 상세 열기")
    @ApiImplicitParam(name = "taskno", value = "과제 pk 번호")
    @GetMapping(value = "/task/{taskno}")
    public ResponseEntity<TaskDetailResponse> taskDetail(@PathVariable("taskno") int taskNo) throws Exception{
        TaskDetailResponse taskDetailResponse = challengeService.getTaskDetail(taskNo);
        return new ResponseEntity<>(taskDetailResponse,HttpStatus.OK);
    }

}
