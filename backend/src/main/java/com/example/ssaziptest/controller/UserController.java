package com.example.ssaziptest.controller;

import com.example.ssaziptest.common.util.JwtTokenUtil;
import com.example.ssaziptest.domain.feed.FeedListResponse;
import com.example.ssaziptest.domain.feed.GalaxyEntryRequest;
import com.example.ssaziptest.domain.file.FileUploadRequest;
import com.example.ssaziptest.domain.follow.FollowRequest;
import com.example.ssaziptest.domain.task.TaskTicketResponse;
import com.example.ssaziptest.domain.user.*;
import com.example.ssaziptest.repository.UserRepository;
import com.example.ssaziptest.service.FeedService;
import com.example.ssaziptest.service.FollowService;
import com.example.ssaziptest.service.UserService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;

import javax.sql.rowset.serial.SerialBlob;
import java.io.BufferedInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.sql.Blob;
import java.sql.SQLException;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Api(tags = {"1.User"})
@RestController
@RequestMapping("/")
public class UserController {
    @Autowired
    private UserService userService;
    @Autowired
    private FollowService followService;
    @Autowired
    private UserRepository userRepository;
    @Autowired
    private FeedService feedService;

    /*회원가입*/
    @ApiOperation(value = "회원 가입")
    @PostMapping(value = "signup")
    public void createUser(@RequestBody UserCreateRequest request) throws Exception {
        userService.createUser(request);
    }

    /*ID 중복체크*/
    @ApiOperation(value = "ID 중복체크")
    @GetMapping(value = "signup/check/{user_email}")
    //public boolean isUsableEmail(@PathVariable("user_email") String user_email) throws Exception{
    public ResponseEntity<Boolean> isUsableEmail(@PathVariable("user_email") String user_email) throws Exception {
        boolean usable = userService.checkEmail(user_email);
        System.out.println(usable);
        //f(usable!=null)//사용가능
        //return new ResponseEntity<Boolean>("suc",usable,HttpStatus.OK);
        return ResponseEntity.ok().body(usable);
        // else return ResponseEntity.status(HttpStatus.NOT_FOUND);
    }

    /*로그인*/
    @ApiOperation(value = "로그인")
    @PostMapping(value = "login")
    //public ResponseEntity<BoolResult> login(@RequestBody LoginRequest request) throws Exception{
    public ResponseEntity<UserLoginPostResponse> login(@RequestBody LoginRequest request) throws Exception {
        UserInfoResponse userInfoResponse = userService.login(request.getUserEmail(), request.getUserPassword());
        if (userInfoResponse == null)
            return ResponseEntity.status(401).body(UserLoginPostResponse.of(401, "Invalid Password", null, userInfoResponse));
        return ResponseEntity.ok(UserLoginPostResponse.of(200, "Success", JwtTokenUtil.getToken(request.getUserEmail()), userInfoResponse));
    }

    /*회원 정보 요청*/
    @ApiOperation(value = "회원 정보 요청")
    @GetMapping(value = "profile/info/{useremail}")
    public ResponseEntity<UserDetailResponse> findUser(@PathVariable("useremail") String userEmail) throws Exception {
        UserDetailResponse userDetailResponse = userService.findUserById(userEmail);
        return new ResponseEntity<>(userDetailResponse, HttpStatus.OK);
    }

    /*회원 정보 수정*/
    @ApiOperation(value = "회원 상세 정보 수정")
    @PutMapping(value = "profile/update")
    public ResponseEntity<UserDetailResponse> updateUser(@RequestBody UserUpdateRequest request) throws Exception {
        UserDetailResponse userDetailResponse = userService.updateUser(request);
        return new ResponseEntity<>(userDetailResponse, HttpStatus.OK);
    }

    /*회원 정보 - 챌린지 과제 제출 현황 티켓*/
    @ApiOperation(value = "챌린지 과제 제출 현황 티켓")
    @GetMapping(value = "profile/taskticket/{useremail}")
    public ResponseEntity<List<TaskTicketResponse>> getTaskTickets(@PathVariable("useremail") String userEmail) throws Exception {
        List<TaskTicketResponse> taskTicketResponseList = userService.getTaskTickets(userEmail);
        return new ResponseEntity<>(taskTicketResponseList, HttpStatus.OK);
    }

    /*팔로우 요청*/
    @ApiOperation(value = "팔로우 요청")
    @PostMapping(value = "follow")
    public void follow(@RequestBody FollowRequest request) throws Exception {
        followService.followUser(request);
    }

    /*팔로우 취소*/
    @ApiOperation(value = "팔로우 취소")
    @DeleteMapping(value = "follow/{useremail}/{followfollower}")
    public void unfollow(@PathVariable(name = "useremail") String useremail, @PathVariable(name = "followfollower") String followfollower) throws Exception {
        FollowRequest request = new FollowRequest();
        request.setUserEmail(useremail);
        request.setFollowFollower(followfollower);
        followService.unfollowUser(request);

    }

    /*팔로워 목록*/
    @ApiOperation(value = "팔로우 목록")
    @GetMapping(value = "follow/followers/{useremail}")
    public ResponseEntity<List<String[]>> getFollowers(@PathVariable(name = "useremail") String userEmail) throws Exception {
        List<String[]> list = followService.getFollowers(userEmail);
        return new ResponseEntity<>(list, HttpStatus.OK);
    }

    /*팔로잉 목록*/
    @ApiOperation(value = "팔로잉 목록")
    @GetMapping(value = "follow/followings/{useremail}")
    public ResponseEntity<List<String[]>> getFollowings(@PathVariable(name = "useremail") String userEmail) throws Exception {
        List<String[]> list = followService.getFollowings(userEmail);
        return new ResponseEntity<>(list, HttpStatus.OK);
    }

    @ApiOperation(value = "유저 프로필 업로드")
    @PostMapping(value = "profile/upload/{useremail}")
    //@ModelAttribute FileUploadRequest request
    public void uploadUserProfile(@RequestParam("file") MultipartFile file, @PathVariable(name = "useremail") String userEmail) throws IOException, SQLException {
//        Map<String, Object> param = new HashMap<>();
//        String filename = file.getOriginalFilename();
//        byte[] bytes;
//        request.setFileName(filename);
//
//        try {
//            bytes = file.getBytes();
//            try {
//                Blob blob = new SerialBlob(bytes);
//                request.setFileData(file);
//            } catch (SQLException throwables) {
//                throwables.printStackTrace();
//            }
//        } catch (IOException e) {
//            e.printStackTrace();
//        }
//        InputStream inputStream = null;
//        try {
//            inputStream = new BufferedInputStream(file.getInputStream());
//            System.out.println(inputStream);
//        } catch (IOException e) {
//            e.printStackTrace();
//        }
        byte[] contents = file.getBytes();
        Blob blob = new SerialBlob(contents);

        FileUploadRequest request = FileUploadRequest.builder()
                .fileData(blob)
                .fileName(file.getName())
                .userEmail(userEmail)
                .build();

//        int fileno = fileService.fileUpload(request);
        UserEntity userEntity = userRepository.getById(userEmail);
        userEntity.setUserImage(blob);
        userRepository.save(userEntity);
//        return fileno;
    }

    @GetMapping(value = "viewimage/{useremail}")
    public String getByteImg(@PathVariable(name = "useremail") String useremail) throws Exception {
        //Blob blob = fileRepository.getById(fileno).getFileData();
        Blob blob = userRepository.getById(useremail).getUserImage();
        int bloblength = (int) blob.length();
        byte[] blobAsBytes = blob.getBytes(1, bloblength);
        blob.free();
        return Arrays.toString(blobAsBytes);
    }

    @ApiOperation(value = "galaxy 입장")
    @PostMapping("/galaxy/entry")
    public void getFeedLists(@RequestBody GalaxyEntryRequest request) throws Exception {
        feedService.sendGalaxyEntranceMessage(request);
    }

}