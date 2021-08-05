package com.example.ssaziptest.controller;

import com.example.ssaziptest.domain.rank.GradsResponse;
import com.example.ssaziptest.domain.rank.RankResponse;
import com.example.ssaziptest.service.RankService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@Api(tags = {"7.Rank"})
@RestController
@RequestMapping(value = "/rank")
public class RankController {

    @Autowired
    private RankService rankService;

    /*갤럭시 Top 5 요청*/
    @ApiOperation(value = "갤럭시 Top 5 요청")
    @GetMapping(value = "/galaxy")
    public ResponseEntity<List<RankResponse>> getGalxyTop5(){
        List<RankResponse> responses = rankService.galaxytop5();
        return new ResponseEntity<>(responses, HttpStatus.OK);
    }
    /*챌린지 Top 5 요청*/
    @ApiOperation(value = "챌린지 Top 5 요청")
    @GetMapping(value = "/challenge")
    public ResponseEntity<List<RankResponse>> getChallengeTop5(){
        List<RankResponse> responses = rankService.challengetop5();
        return new ResponseEntity<>(responses, HttpStatus.OK);
    }
    /*졸업자 랜덤 3명 요청*/
    @ApiOperation(value = "졸업자 랜덤 3명 요청")
    @GetMapping(value = "/graduated")
    public ResponseEntity<List<GradsResponse>> getGraduates(){
        List<GradsResponse> responses = rankService.getGraduates();
        return new ResponseEntity<>(responses,HttpStatus.OK);
    }

}
