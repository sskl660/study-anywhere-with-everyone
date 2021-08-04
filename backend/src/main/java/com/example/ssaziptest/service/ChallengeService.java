package com.example.ssaziptest.service;

import com.example.ssaziptest.domain.challenge.ChallengeCreateRequest;
import com.example.ssaziptest.domain.challenge.ChallengeDetailResponse;
import com.example.ssaziptest.domain.challenge.ChallengeEntity;
import com.example.ssaziptest.domain.challenge.ChallengeListResponse;
import com.example.ssaziptest.domain.feed.FeedEntity;
import com.example.ssaziptest.domain.follow.FollowEntity;
import com.example.ssaziptest.domain.group.GroupMemberRequest;
import com.example.ssaziptest.domain.group.GroupmemberEntity;
import com.example.ssaziptest.domain.task.BulletJournalResponse;
import com.example.ssaziptest.domain.task.TaskDetailResponse;
import com.example.ssaziptest.domain.task.TaskEntity;
import com.example.ssaziptest.domain.user.UserEntity;
import com.example.ssaziptest.repository.*;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
@AllArgsConstructor
@NoArgsConstructor
public class ChallengeService {

    @Autowired
    private ChallengeRepository challengeRepository;
    @Autowired
    private UserRepository userRepository;
    @Autowired
    private GroupmemberRepository groupmemberRepository;
    @Autowired
    private TaskRepository taskRepository;
    @Autowired
    private FollowRepository followRepository;
    @Autowired
    private FeedRepository feedRepository;

    @Transactional
    public int createChallenge(ChallengeCreateRequest request) {
        return challengeRepository.save(request.toEntity()).getChallengeNo();
    }

    @Transactional
    public List<ChallengeListResponse> getChallengelist() {
        List<ChallengeEntity> challengeEntities = challengeRepository.findAll();
        List<ChallengeListResponse> challengeDtoList = new ArrayList<>();

        for (ChallengeEntity challengeEntity : challengeEntities) {
            // System.out.println(challengeEntity.getChallengeName());
            ChallengeListResponse challengeDto = ChallengeListResponse.builder()
                    .challengeNo(challengeEntity.getChallengeNo())
                    .challengeName(challengeEntity.getChallengeName())
                    .challengeCategory(challengeEntity.getChallengeCategory())
                    .challengeLevel(challengeEntity.getChallengeLevel())
                    .challengeCapacity(challengeEntity.getChallengeCapacity())
                    .challengeStartdate(challengeEntity.getChallengeStartdate())
                    .challengeEnddate(challengeEntity.getChallengeEnddate())
                    .challengeDesc(challengeEntity.getChallengeDesc())
                    .challengeTaskCnt(challengeEntity.getChallengeTaskCnt())
                    .build();
            challengeDtoList.add(challengeDto);
        }
        return challengeDtoList;
    }

    @Transactional
    public ChallengeDetailResponse getChallengeDetail(int challengeNo) {
        ChallengeEntity challengeEntity = challengeRepository.findById(challengeNo).orElse(null);
        if(challengeEntity!=null){
            ChallengeDetailResponse challengeDetailResponse = ChallengeDetailResponse.builder()
                    .challengeNo(challengeEntity.getChallengeNo())
                    .challengeName(challengeEntity.getChallengeName())
                    .challengeCategory(challengeEntity.getChallengeCategory())
                    .challengeLevel(challengeEntity.getChallengeLevel())
                    .challengeCapacity(challengeEntity.getChallengeCapacity())
                    .challengeStartdate(challengeEntity.getChallengeStartdate())
                    .challengeEnddate(challengeEntity.getChallengeEnddate())
                    .challengeDesc(challengeEntity.getChallengeDesc())
                    .challengeTaskCnt(challengeEntity.getChallengeTaskCnt())
                    .challengeTaskdeadlines(challengeEntity.getChallengeTaskdeadlines())
                    .build();
            List<String[]> members = new ArrayList<>();
            List<GroupmemberEntity> groupmemberEntities = groupmemberRepository.findByGroupChallengeEntity_ChallengeNo(challengeNo);
            for(GroupmemberEntity groupmemberEntity: groupmemberEntities){
                String[] temp = new String[2];
                temp[0] = groupmemberEntity.getGroupUserEntity().getUserEmail();
                temp[1] = groupmemberEntity.getGroupUsername();
                members.add(temp);
            }
            challengeDetailResponse.setChallengeGroup(members);
            return challengeDetailResponse;
        }
        else return null;
    }

    @Transactional
    public void joinChallenge(String userEmail, int challengeNo){
        UserEntity userEntity = userRepository.findById(userEmail).orElse(null);
        ChallengeEntity challengeEntity = challengeRepository.findById(challengeNo).orElse(null);

        GroupMemberRequest request = new GroupMemberRequest();
        request.setUserEntity(userEntity);
        request.setChallengeEntity(challengeEntity);
        request.setGroupUsername(userEntity.getUserName());

        groupmemberRepository.save(request.toEntity());

        List<FollowEntity> followEntities = followRepository.findByFollowUserEntity_UserEmail(userEmail);
        //팔로워들한테 다 save
        for(FollowEntity followEntity: followEntities){
            FeedEntity feedEntity = FeedEntity.builder()
                    .feedType(1)
                    .feedOwner(followEntity.getFollowFollower())
                    .feedUserEntity(userEntity)
                    .feedInfo(Integer.toString(challengeNo))
                    .build();
            feedRepository.save(feedEntity);
        }
    }

    @Transactional
    public List<BulletJournalResponse> getTaskList(int challengeno){
        List<TaskEntity> taskEntityList = taskRepository.findByTaskChallengeEntity_ChallengeNo(challengeno);
        List<BulletJournalResponse> bjList = new ArrayList<>();
        for(TaskEntity taskEntity: taskEntityList){
            BulletJournalResponse bjresponse = BulletJournalResponse.builder()
                    .taskNo(taskEntity.getTaskNo())
                    .taskIndex(taskEntity.getTaskIndex())
                    .userName(taskEntity.getTaskUserEntity().getUserName())
                    .userEmail(taskEntity.getTaskUserEntity().getUserEmail())
                    .build();
            bjList.add(bjresponse);
        }
        return bjList;
    }

    @Transactional
    public TaskDetailResponse getTaskDetail(int taskNo){
        TaskEntity taskEntity = taskRepository.findById(taskNo).orElse(null);
        TaskDetailResponse taskDetailResponse = new TaskDetailResponse();
        if(taskEntity!=null){
            taskDetailResponse.setTaskNo(taskNo);
            taskDetailResponse.setTaskIndex(taskEntity.getTaskIndex());
            taskDetailResponse.setTaskContent(taskEntity.getTaskContent());
            taskDetailResponse.setTaskDesc(taskEntity.getTaskDesc());
            taskDetailResponse.setTaskImage(taskEntity.getTaskImage());
            taskDetailResponse.setTaskFile(taskEntity.getTaskFile());
            taskDetailResponse.setUserEmail(taskEntity.getTaskUserEntity().getUserEmail());
            taskDetailResponse.setUserName(taskEntity.getTaskUserEntity().getUserName());
            taskDetailResponse.setLikes(taskEntity.getTaskLikes().size());
            taskDetailResponse.setLikemembers(taskEntity.getTaskLikes());
        }
        return taskDetailResponse;
    }
}
