package com.example.ssaziptest.controller;

import com.example.ssaziptest.domain.follow.FollowRequest;
import com.example.ssaziptest.domain.result.BoolResult;
import com.example.ssaziptest.domain.task.TaskTicketResponse;
import com.example.ssaziptest.domain.user.*;
import com.example.ssaziptest.service.FollowService;
import com.example.ssaziptest.service.UserService;
import com.google.api.Http;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/")
public class UserController {
    @Autowired
    private UserService userService;
    @Autowired
    private FollowService followService;

    /*회원가입*/
    @PostMapping(value = "signup")
    public void createUser(@RequestBody UserCreateRequest request) throws Exception{
        userService.createUser(request);
    }
    /*ID 중복체크*/
    @GetMapping(value = "signup/check/{user_email}")
    public boolean isUsableEmail(@PathVariable("user_email") String user_email) throws Exception{
        return userService.checkEmail(user_email);
    }
    /*로그인*/
    @PostMapping(value = "login")
    public ResponseEntity<BoolResult> login(@RequestBody LoginRequest request) throws Exception{
        UserEntity userEntity = userService.login(request.getUserEmail(), request.getUserPassword());
        BoolResult br = new BoolResult();
        br.setName("login");
        br.setState("this istooooooken");
        if(userEntity != null) br.setResult(true);
        return new ResponseEntity<>(br, HttpStatus.OK);
    }
    /*회원 정보 요청*/
    @GetMapping(value = "profile/info/{useremail}")
    public ResponseEntity<UserDetailResponse> findUser(@PathVariable("useremail") String userEmail) throws Exception{
        UserDetailResponse userDetailResponse = userService.findUserById(userEmail);
        return new ResponseEntity<>(userDetailResponse,HttpStatus.OK);
    }
    /*회원 정보 수정*/
    @PutMapping(value = "profile/update")
    public ResponseEntity<UserDetailResponse> updateUser(@RequestBody UserUpdateRequest request) throws Exception{
        UserDetailResponse userDetailResponse = userService.updateUser(request);
        return new ResponseEntity<>(userDetailResponse, HttpStatus.OK);
    }
    /*회원 정보 - 챌린지 과제 제출 현황 티켓*/
    @GetMapping(value = "profile/taskticket/{useremail}")
    public ResponseEntity<List<TaskTicketResponse>> getTaskTickets(@PathVariable("useremail") String userEmail) throws Exception{
        List<TaskTicketResponse> taskTicketResponseList = userService.getTaskTickets(userEmail);
        return new ResponseEntity<>(taskTicketResponseList, HttpStatus.OK);
    }
    /*팔로우 요청*/
    @PostMapping(value = "follow")
    public void follow(@RequestBody FollowRequest request) throws Exception{
        followService.followUser(request);
    }
    /*팔로우 취소*/
    @DeleteMapping(value = "follow/{useremail}/{followfollower}")
    public void unfollow(@PathVariable(name = "useremail")String useremail, @PathVariable(name = "followfollower") String followfollower) throws Exception{
        FollowRequest request = new FollowRequest();
        request.setUserEmail(useremail);
        request.setFollowFollower(followfollower);
        followService.unfollowUser(request);

    }
    /*팔로워 목록*/
    @GetMapping(value = "follow/followers/{useremail}")
    public ResponseEntity<List<String[]>> getFollowers(@PathVariable(name = "useremail")String userEmail) throws Exception{
        List<String[]> list = followService.getFollowers(userEmail);
        return new ResponseEntity<>(list,HttpStatus.OK);
    }
    /*팔로잉 목록*/
    @GetMapping(value = "follow/followings/{useremail}")
    public ResponseEntity<List<String[]>> getFollowings(@PathVariable(name = "useremail")String userEmail) throws Exception{
        List<String[]> list = followService.getFollowings(userEmail);
        return new ResponseEntity<>(list,HttpStatus.OK);
    }
}
