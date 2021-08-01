package com.example.ssaziptest.service;

import com.example.ssaziptest.domain.challenge.ChallengeCreateRequest;
import com.example.ssaziptest.domain.challenge.ChallengeDetailResponse;
import com.example.ssaziptest.domain.challenge.ChallengeEntity;
import com.example.ssaziptest.domain.challenge.ChallengeListResponse;
import com.example.ssaziptest.domain.group.GroupMemberRequest;
import com.example.ssaziptest.domain.task.BulletJournalResponse;
import com.example.ssaziptest.domain.task.TaskDetailResponse;
import com.example.ssaziptest.domain.task.TaskEntity;
import com.example.ssaziptest.domain.user.UserEntity;
import com.example.ssaziptest.repository.ChallengeRepository;
import com.example.ssaziptest.repository.GroupmemberRepository;
import com.example.ssaziptest.repository.TaskRepository;
import com.example.ssaziptest.repository.UserRepository;
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
        Optional<ChallengeEntity> challengeEntityTemp = challengeRepository.findById(challengeNo);
        ChallengeEntity challengeEntity = challengeEntityTemp.orElse(null);
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
            return challengeDetailResponse;
        }
        else return null;
    }

    @Transactional
    public void joinChallenge(String userEmail, int challengeNo){
        Optional<UserEntity> userEntityTemp = userRepository.findById(userEmail);
        Optional<ChallengeEntity> challengeEntityTemp = challengeRepository.findById(challengeNo);
        UserEntity userEntity = userEntityTemp.orElse(null);
        ChallengeEntity challengeEntity = challengeEntityTemp.orElse(null);

        GroupMemberRequest request = new GroupMemberRequest();
        request.setUserEntity(userEntity);
        request.setChallengeEntity(challengeEntity);
        request.setGroupUsername(userEntity.getUserName());

        groupmemberRepository.save(request.toEntity());
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
        }
        return taskDetailResponse;
    }
}
