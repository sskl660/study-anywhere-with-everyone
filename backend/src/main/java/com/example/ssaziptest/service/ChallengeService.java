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
import com.example.ssaziptest.domain.task.ChallengeTicketResponse;
import com.example.ssaziptest.domain.task.TaskDetailResponse;
import com.example.ssaziptest.domain.task.TaskEntity;
import com.example.ssaziptest.domain.user.UserEntity;
import com.example.ssaziptest.repository.*;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.sql.Blob;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Arrays;
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
    public int updateChallenge(int challengeNo, ChallengeCreateRequest request){
        ChallengeEntity challengeEntity = challengeRepository.findById(challengeNo).orElse(null);
        challengeEntity.setChallengeName(request.getChallengeName());
        challengeEntity.setChallengeCategory(request.getChallengeCategory());
        challengeEntity.setChallengeLevel(request.getChallengeLevel());
        challengeEntity.setChallengeCapacity(request.getChallengeCapacity());
        challengeEntity.setChallengeStartdate(request.getChallengeStartdate());
        challengeEntity.setChallengeEnddate(request.getChallengeEnddate());
        challengeEntity.setChallengeTaskCnt(request.getChallengeTaskCnt());
        challengeEntity.setChallengeTaskdeadlines(new ArrayList<>(Arrays.asList(request.getChallengeTaskdeadlines())));
        challengeRepository.save(challengeEntity);
        return challengeNo;
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
        //?????????????????? ??? save
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
        //?????????
        ChallengeEntity challengeEntity = challengeRepository.findById(challengeno).get();
        //????????? ????????? ?????? ??????
        List<LocalDate> deadlines = challengeEntity.getChallengeTaskdeadlines();
        //???????????? ?????? ??? ?????????
        List<TaskEntity> taskEntityList = taskRepository.findByTaskChallengeEntity_ChallengeNo(challengeno);
        //?????? ?????????
        List<GroupmemberEntity> groupmemberEntities = groupmemberRepository.findByGroupChallengeEntity_ChallengeNo(challengeno);
        //int membercnt = groupmemberEntities.size();

        List<BulletJournalResponse> bjList = new ArrayList<>();
        for(GroupmemberEntity groupmemberEntity:groupmemberEntities){
            BulletJournalResponse response = new BulletJournalResponse();
            Integer[] temp = new Integer[challengeEntity.getChallengeTaskCnt()];
            for(TaskEntity taskEntity: taskEntityList){
                if(taskEntity.getTaskUserEntity().getUserEmail() == groupmemberEntity.getGroupUserEntity().getUserEmail()){
                    temp[taskEntity.getTaskIndex()] = taskEntity.getTaskNo();
                }
            }
            for(int i=0; i<challengeEntity.getChallengeTaskCnt(); i++){
                if(temp[i]==null){
                    if(deadlines.get(i).isBefore(LocalDate.now())) temp[i] = -2;
                    else temp[i] = -1;
                }
            }
            response.setTaskNo(temp);
            response.setUserEmail(groupmemberEntity.getGroupUserEntity().getUserEmail());
            response.setUserName(groupmemberEntity.getGroupUsername());

            bjList.add(response);
        }
        return bjList;
    }
    @Transactional
    public ChallengeTicketResponse[] getChallengeTicket(int challengeNo){
        ChallengeEntity challengeEntity = challengeRepository.getById(challengeNo);
        List<TaskEntity> taskEntityList = taskRepository.findByTaskChallengeEntity_ChallengeNo(challengeNo);
        List<LocalDate> taskDeadlines = challengeEntity.getChallengeTaskdeadlines();
        ChallengeTicketResponse[] temp = new ChallengeTicketResponse[challengeEntity.getChallengeTaskCnt()];
        for(int i = 0; i<challengeEntity.getChallengeTaskCnt(); i++){
            temp[i] = new ChallengeTicketResponse();
        }
        int[] arr = new int[challengeEntity.getChallengeTaskCnt()];
        for(int i = 0; i<taskDeadlines.size(); i++){
            if(taskDeadlines.get(i).isAfter(LocalDate.now().minusDays(1))) temp[i].setInProgress(true);
        }
        for(TaskEntity taskEntity: taskEntityList){
            arr[taskEntity.getTaskIndex()]++;
        }
        int membercnt = groupmemberRepository.findByGroupChallengeEntity_ChallengeNo(challengeNo).size();
        for(int i=0; i<taskDeadlines.size(); i++){
            temp[i].setAchieveRate(100*arr[i]/membercnt);
        }
        return temp;
    }

    @Transactional
    public TaskDetailResponse getTaskDetail(int taskNo) throws Exception{
        TaskEntity taskEntity = taskRepository.findById(taskNo).orElse(null);
        TaskDetailResponse taskDetailResponse = new TaskDetailResponse();
        if(taskEntity!=null){
            if(taskEntity.getTaskImage()!=null){
                Blob blob = taskEntity.getTaskImage();
                int bloblength = (int)blob.length();
                byte[] blobAsBytes = blob.getBytes(1,bloblength);
                blob.free();
                taskDetailResponse.setTaskImage(Arrays.toString(blobAsBytes));
            }
            if(taskEntity.getTaskFile()!=null){
//                Blob blob = taskEntity.getTaskFile();
//                int bloblength = (int)blob.length();
//                byte[] blobAsBytes = blob.getBytes(1,bloblength);
//                blob.free();
//                taskDetailResponse.setTaskFile(Arrays.toString(blobAsBytes));
                taskDetailResponse.setTaskFile("file exists");
            }
//            taskDetailResponse.setTaskFileName();
            taskDetailResponse.setTaskNo(taskNo);
            taskDetailResponse.setTaskIndex(taskEntity.getTaskIndex());
            taskDetailResponse.setTaskContent(taskEntity.getTaskContent());
            taskDetailResponse.setTaskDesc(taskEntity.getTaskDesc());
            taskDetailResponse.setTaskFileName(taskEntity.getTaskFileName());
            taskDetailResponse.setTaskFileType(taskEntity.getTaskFileType());
            taskDetailResponse.setUserEmail(taskEntity.getTaskUserEntity().getUserEmail());
            taskDetailResponse.setUserName(taskEntity.getTaskUserEntity().getUserName());
            taskDetailResponse.setUserTerm(taskEntity.getTaskUserEntity().getUserTerm());
            taskDetailResponse.setLikes(taskEntity.getTaskLikes().size());
            taskDetailResponse.setLikemembers(taskEntity.getTaskLikes());
        }
        return taskDetailResponse;
    }

    @Transactional
    public List<ChallengeListResponse> searchChallenges(String keyword){
        List<ChallengeEntity> challengeEntities = challengeRepository.findByChallengeNameContaining(keyword);
        List<GroupmemberEntity> groupmemberEntities = groupmemberRepository.findByGroupUsernameContaining(keyword);
        for(GroupmemberEntity groupmemberEntity:groupmemberEntities){
            ChallengeEntity challengeEntity = challengeRepository.findById(groupmemberEntity.getGroupChallengeEntity().getChallengeNo()).orElse(null);
            challengeEntities.add(challengeEntity);
        }
        List<ChallengeListResponse> list = new ArrayList<>();
        for(ChallengeEntity challengeEntity : challengeEntities){
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
            list.add(challengeDto);
        }
        return list;
    }

}
