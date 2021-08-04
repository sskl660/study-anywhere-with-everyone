package com.example.ssaziptest.service;

import com.example.ssaziptest.domain.challenge.ChallengeEntity;
import com.example.ssaziptest.domain.group.GroupmemberEntity;
import com.example.ssaziptest.domain.task.TaskEntity;
import com.example.ssaziptest.domain.task.TaskTicketResponse;
import com.example.ssaziptest.domain.user.*;
import com.example.ssaziptest.repository.ChallengeRepository;
import com.example.ssaziptest.repository.GroupmemberRepository;
import com.example.ssaziptest.repository.TaskRepository;
import com.example.ssaziptest.repository.UserRepository;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;

import javax.transaction.Transactional;
import java.util.ArrayList;
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
        userRepository.save(request.toEntity());
    }
    @Transactional
    public UserDetailResponse updateUser(UserUpdateRequest request){
        UserEntity userEntity = userRepository.findById(request.getUserEmail()).orElse(null);
        userEntity.setUserImage(request.getUserImage());
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

    @Transactional
    public boolean checkEmail(String user_email){
        Optional<UserEntity> userEntity = userRepository.findById(user_email);
        //중복일때 false, 사용가능할때 true
        System.out.println(userEntity);
        return userEntity.isEmpty()?true:false;
    }

    @Transactional
    public UserEntity login(String userEmail, String userPassword){
        Optional<UserEntity> userEntity = userRepository.findUserEntityByUserEmailAndUserPassword(userEmail, userPassword);
        return userEntity.orElse(null);
    }
    @Transactional
    public UserDetailResponse findUserById(String userEmail){
        UserEntity userEntity = userRepository.findById(userEmail).orElse(null);
        if(userEntity!=null){
            UserDetailResponse userDetailResponse = UserDetailResponse.builder()
                    .userEmail(userEntity.getUserEmail())
                    .userName(userEntity.getUserName())
                    .userNumber(userEntity.getUserNumber())
                    .userTerm(userEntity.getUserTerm())
                    .userGraduated(userEntity.getUserGraduated())
                    .userTotalcomplete(userEntity.getUserTotalcomplete())
                    .userImage(userEntity.getUserImage())
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
            int taskcnt= challengeEntity.getChallengeTaskCnt();
            taskTicketResponse.setChallengeTaskCnt(taskcnt);
            Integer[] temp = new Integer[taskcnt];
            for(TaskEntity taskEntity: taskEntities){
                temp[taskEntity.getTaskIndex()] = taskEntity.getTaskNo();
            }
            taskTicketResponse.setTaskNo(temp);

            for(Integer in: taskTicketResponse.getTaskNo()){
                if(in==null) taskTicketResponse.setIsComplete(false);
                break;
            }

            taskTicketResponseList.add(taskTicketResponse);
        }
        return taskTicketResponseList;
    }

    //jwt
    public UserEntity getUserByUserId(String email) {
        // 디비에 유저 정보 조회 (userId 를 통한 조회).
        //User user = userRepositorySupport.findUserByUserId(uid).get();
        UserEntity user= userRepository.getById(email);
        return user;
    }
}
