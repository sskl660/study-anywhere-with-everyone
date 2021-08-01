package com.example.ssaziptest.controller;

import com.example.ssaziptest.domain.challenge.ChallengeCreateRequest;
import com.example.ssaziptest.domain.challenge.ChallengeDetailResponse;
import com.example.ssaziptest.domain.challenge.ChallengeListResponse;
import com.example.ssaziptest.service.ChallengeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/challengelist")
public class ChallengeListController {
    @Autowired
    private ChallengeService challengeService;

    @PostMapping(value = "create")
    public ResponseEntity<Integer> createChallenge(@RequestBody ChallengeCreateRequest request) throws Exception{

        int response = challengeService.createChallenge(request);
        return new ResponseEntity<Integer>(response, HttpStatus.OK);
    }
    @GetMapping(value = "")
    public ResponseEntity<List<ChallengeListResponse>> challengeList() throws Exception{
        List<ChallengeListResponse> challenges = challengeService.getChallengelist();
        //System.out.println(challenges.get(0).getChallengeName());
        if(challenges.isEmpty()) return new ResponseEntity<>(null,HttpStatus.NO_CONTENT);
        return new ResponseEntity<List<ChallengeListResponse>>(challenges,HttpStatus.OK);
    }



}
