package com.example.ssaziptest.service;

import com.example.ssaziptest.domain.challenge.ChallengeEntity;
import com.example.ssaziptest.domain.group.GroupmemberEntity;
import com.example.ssaziptest.domain.task.TaskEntity;
import com.example.ssaziptest.domain.task.TaskTicketResponse;
import com.example.ssaziptest.domain.user.*;
import com.example.ssaziptest.repository.*;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import org.apache.tomcat.jni.Local;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.sql.Blob;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.temporal.ChronoUnit;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;

@Service
@AllArgsConstructor
@NoArgsConstructor
public class UserService {
    @Autowired
    private UserRepository userRepository;
    @Autowired
    private TaskRepository taskRepository;
    @Autowired
    private ChallengeRepository challengeRepository;
    @Autowired
    private GroupmemberRepository groupmemberRepository;

    @Transactional
    public void createUser(UserCreateRequest request){
        if(request.getUserTerm()<5) request.setUserGraduated(true);
        userRepository.save(request.toEntity());
    }
    @Transactional
    public UserDetailResponse updateUser(UserUpdateRequest request) throws Exception{
//        byte[] contents = file.getBytes();
//        Blob blob = new SerialBlob(contents);
        UserEntity userEntity = userRepository.findById(request.getUserEmail()).orElse(null);
//        userEntity.setUserImage(blob);
        userEntity.setUserGit(request.getUserGit());
        userEntity.setUserBlog(request.getUserBlog());
        userEntity.setUserDevstyle(request.getUserDevstyle());
        userEntity.setUserMbti(request.getUserMbti());
        userEntity.setUserWishfield(request.getUserWishfield());
        userEntity.setUserIntroduce(request.getUserIntroduce());
        userEntity.setUserTechstack(request.getUserTechstack());
        userRepository.save(userEntity);
        return findUserById(request.getUserEmail());
    }
//    @Transactional
//    public void insertUserImg(FileUploadRequest request){
//        List<FileEntity> temp = fileRepository.findByFileUseremailAndFileInfo(request.getUserEmail(), request.getFileInfo());
//        FileEntity fileEntity = temp.get(0);
//        fileEntity.setFileName(request.getFileName());
//        fileEntity.setFileOriginalname(request.getFileOriginalname());
//        fileRepository.save(fileEntity);
//
//    }


    @Transactional
    public boolean checkEmail(String user_email){
        Optional<UserEntity> userEntity = userRepository.findById(user_email);
        //중복일때 false, 사용가능할때 true
        System.out.println(userEntity);
        return userEntity.isEmpty()?true:false;
    }

    @Transactional
    public UserInfoResponse login(String userEmail, String userPassword){
        Optional<UserInfoResponse> userInfoResponse = userRepository.findUserEntityByUserEmailAndUserPassword(userEmail, userPassword);
        return userInfoResponse.orElse(null);
    }
    @Transactional
    public UserDetailResponse findUserById(String userEmail) throws Exception{
        UserEntity userEntity = userRepository.findById(userEmail).orElse(null);
        if(userEntity!=null){


            UserDetailResponse userDetailResponse = UserDetailResponse.builder()
                    .userEmail(userEntity.getUserEmail())
                    .userName(userEntity.getUserName())
                    .userNumber(userEntity.getUserNumber())
                    .userTerm(userEntity.getUserTerm())
                    .userGraduated(userEntity.getUserGraduated())
                    .userTotalcomplete(userEntity.getUserTotalcomplete())
                    .userGit(userEntity.getUserGit())
                    .userBlog(userEntity.getUserBlog())
                    .userDevstyle(userEntity.getUserDevstyle())
                    .userMbti(userEntity.getUserMbti())
                    .userWishfield(userEntity.getUserWishfield())
                    .userIntroduce(userEntity.getUserIntroduce())
                    .userTechstack(userEntity.getUserTechstack())
                    .userTotaltime(userEntity.getUserTotaltime())
                    .userWeektime(userEntity.getUserWeektime())
                    .userWeekcomplete(userEntity.getUserWeekcomplete())
                    .userFollower(userEntity.getUserFollower())
                    .userFollowing(userEntity.getUserFollowing())
                    .build();
//            if (userEntity.getUserImage() != null) {
//                Blob blob = userEntity.getUserImage();
//                int bloblength = (int)blob.length();
//                byte[] blobAsBytes = blob.getBytes(1,bloblength);
//                blob.free();
//                userDetailResponse.setUserImage(Arrays.toString(blobAsBytes));
//            }
            return userDetailResponse;
        }
        return null;
    }

    @Transactional
    public List<TaskTicketResponse> getTaskTickets(String userEmail){
        List<GroupmemberEntity> groupmemberEntities = groupmemberRepository.findByGroupUserEntity_UserEmail(userEmail);
        List<TaskTicketResponse> taskTicketResponseList = new ArrayList<>();
        for(GroupmemberEntity groupmemberEntity: groupmemberEntities){
            TaskTicketResponse taskTicketResponse = new TaskTicketResponse();
            ChallengeEntity challengeEntity = challengeRepository.findById(groupmemberEntity.getGroupChallengeEntity().getChallengeNo()).orElse(null);
            List<TaskEntity> taskEntities = taskRepository.findByTaskChallengeEntity_ChallengeNoAndTaskUserEntity_UserEmail(challengeEntity.getChallengeNo(),userEmail);
            taskTicketResponse.setChallengeNo(challengeEntity.getChallengeNo());
            taskTicketResponse.setChallengeName(challengeEntity.getChallengeName());
            int taskcnt= challengeEntity.getChallengeTaskCnt();
            taskTicketResponse.setChallengeTaskCnt(taskcnt);
            Integer[] temp = new Integer[taskcnt];
            for(TaskEntity taskEntity: taskEntities){
                temp[taskEntity.getTaskIndex()] = taskEntity.getTaskNo();
            }
            taskTicketResponse.setTaskNo(temp);
            int done = 0;
            List<LocalDate> deadlines = challengeEntity.getChallengeTaskdeadlines();
            for(Integer in: taskTicketResponse.getTaskNo()){
//                if(in==null) {
//                    taskTicketResponse.setIsComplete(false);
//                }
                if(in!=null) done++;
            }
            LocalDate enddate = challengeEntity.getChallengeEnddate();
            taskTicketResponse.setIsComplete(enddate.isBefore(LocalDate.now())?true:false);
            for(int i=0; i<taskcnt; i++){
                if(temp[i]==null){
                    if(deadlines.get(i).isBefore(LocalDate.now())) temp[i] = -2;
                    else temp[i] = -1;
                }
            }
            taskTicketResponse.setTaskNo(temp);
            taskTicketResponse.setAchieveRate((100*done)/taskcnt);
            taskTicketResponseList.add(taskTicketResponse);
        }
        return taskTicketResponseList;
    }

    @Transactional
    public void accumulateGalaxyTime(String userEmail, LocalDateTime startTime){
        LocalDateTime endTime = LocalDateTime.now();
        int accumSecond = (int) ChronoUnit.SECONDS.between(startTime,endTime);
        UserEntity userEntity = userRepository.getById(userEmail);
        userEntity.setUserTotaltime(userEntity.getUserTotaltime()+accumSecond);
        userEntity.setUserWeektime(userEntity.getUserWeektime()+accumSecond);
        userRepository.save(userEntity);
    }

    //jwt
    public UserEntity getUserByUserId(String email) {
        // 디비에 유저 정보 조회 (userId 를 통한 조회).
        //User user = userRepositorySupport.findUserByUserId(uid).get();
        UserEntity user= userRepository.getById(email);
        return user;
    }
}
