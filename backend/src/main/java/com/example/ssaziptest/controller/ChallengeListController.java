package com.example.ssaziptest.controller;

import com.example.ssaziptest.domain.challenge.ChallengeCreateRequest;
import com.example.ssaziptest.domain.challenge.ChallengeListResponse;
import com.example.ssaziptest.service.ChallengeService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Api(tags = {"3.Challenge List"})
@RestController
@RequestMapping("/challengelist")
public class ChallengeListController {
    @Autowired
    private ChallengeService challengeService;

    /*챌린지 생성*/
    @ApiOperation(value = "챌린지 생성")
    @PostMapping(value = "create")
    public ResponseEntity<Integer> createChallenge(@RequestBody ChallengeCreateRequest request) throws Exception{
        int response = challengeService.createChallenge(request);
        return new ResponseEntity<Integer>(response, HttpStatus.OK);
    }
    /*챌린지 생성*/
    @ApiOperation(value = "챌린지 수정")
    @PutMapping(value = "update/{challengeno}")
    public ResponseEntity<Integer> updateChallenge(@PathVariable(name = "challengeno")int challengeno, @RequestBody ChallengeCreateRequest request) throws Exception{
        int response = challengeService.updateChallenge(challengeno,request);
        return new ResponseEntity<Integer>(response, HttpStatus.OK);
    }
    /*챌린지 목록 조회*/
    @ApiOperation(value = "챌린지 목록 조회")
    @GetMapping(value = "")
    public ResponseEntity<List<ChallengeListResponse>> challengeList() throws Exception{
        List<ChallengeListResponse> challenges = challengeService.getChallengelist();
        //System.out.println(challenges.get(0).getChallengeName());
        if(challenges.isEmpty()) return new ResponseEntity<>(null,HttpStatus.NO_CONTENT);
        return new ResponseEntity<List<ChallengeListResponse>>(challenges,HttpStatus.OK);
    }
    /*챌린지 목록 검색*/
    @ApiOperation(value = "챌린지 목록 검색",notes = "챌린지명 또는 참가자로 검색")
    @ApiImplicitParam(name = "keyword", value = "검색어")
    @GetMapping(value = "/{keyword}")
    public ResponseEntity<List<ChallengeListResponse>> searchChallenges(@PathVariable(name = "keyword") String keyword) throws  Exception{
        List<ChallengeListResponse> list = challengeService.searchChallenges(keyword);
        return new ResponseEntity<>(list,HttpStatus.OK);
    }
}
