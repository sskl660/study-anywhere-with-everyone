package com.example.ssaziptest.controller;

import com.example.ssaziptest.domain.feed.FeedListResponse;
import com.example.ssaziptest.service.FeedService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/feed")
public class FeedController {
    @Autowired
    private FeedService feedService;

    @GetMapping("/{useremail}")
    public ResponseEntity<List<FeedListResponse>> getFeedLists(@PathVariable(name = "useremail")String userEmail) throws Exception{
        List<FeedListResponse> list = feedService.getChallengeFeeds(userEmail);
        return new ResponseEntity<>(list, HttpStatus.OK);
    }
}
