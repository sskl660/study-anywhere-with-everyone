package com.example.ssaziptest.controller;

import com.example.ssaziptest.domain.result.BoolResult;
import com.example.ssaziptest.domain.user.LoginRequest;
import com.example.ssaziptest.domain.user.UserCreateRequest;
import com.example.ssaziptest.domain.user.UserDetailResponse;
import com.example.ssaziptest.domain.user.UserEntity;
import com.example.ssaziptest.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/")
public class UserController {
    @Autowired
    private UserService userService;

    @PostMapping(value = "signup")
    public void createUser(@RequestBody UserCreateRequest request) throws Exception{
        userService.createUser(request);
    }

    @GetMapping(value = "signup/check/{user_email}")
    public boolean isUsableEmail(@PathVariable("user_email") String user_email) throws Exception{
        return userService.checkEmail(user_email);
    }

    @PostMapping(value = "login")
    public ResponseEntity<BoolResult> login(@RequestBody LoginRequest request) throws Exception{
        UserEntity userEntity = userService.login(request.getUserEmail(), request.getUserPassword());
        BoolResult br = new BoolResult();
        br.setName("login");
        br.setState("succ");
        if(userEntity != null) br.setResult(true);
        return new ResponseEntity<>(br, HttpStatus.OK);
    }
    @GetMapping(value = "profile/info/{useremail}")
    public ResponseEntity<UserDetailResponse> findUser(@PathVariable("useremail") String userEmail) throws Exception{
        UserDetailResponse userDetailResponse = userService.findUserById(userEmail);
        return new ResponseEntity<>(userDetailResponse,HttpStatus.OK);
    }
}
