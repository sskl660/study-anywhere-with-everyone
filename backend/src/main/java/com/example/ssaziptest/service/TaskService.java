package com.example.ssaziptest.service;

import com.example.ssaziptest.domain.challenge.ChallengeListResponse;
import com.example.ssaziptest.domain.feed.FeedEntity;
import com.example.ssaziptest.domain.follow.FollowEntity;
import com.example.ssaziptest.domain.group.GroupmemberEntity;
import com.example.ssaziptest.domain.task.LikeRequest;
import com.example.ssaziptest.domain.task.TaskEntity;
import com.example.ssaziptest.domain.task.TaskSubmitRequest;
import com.example.ssaziptest.domain.task.TaskUpdateRequest;
import com.example.ssaziptest.domain.user.UserEntity;
import com.example.ssaziptest.repository.*;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.scheduling.config.Task;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.time.LocalDate;
import java.util.List;

@Service
@AllArgsConstructor
@NoArgsConstructor
public class TaskService {

    @Autowired
    private TaskRepository taskRepository;
    @Autowired
    private UserRepository userRepository;
    @Autowired
    private ChallengeRepository challengeRepository;

    @Autowired
    private ChallengeService challengeService;
    @Autowired
    private GroupmemberRepository groupmemberRepository;

    @Autowired
    private FollowRepository followRepository;
    @Autowired
    private  FeedRepository feedRepository;


    @Transactional
    public void submitTask(TaskSubmitRequest request){

        TaskEntity taskEntity = TaskEntity.builder()
                .taskUserEntity(userRepository.getById(request.getUserEmail()))
                .taskChallengeEntity(challengeRepository.getById(request.getChallengeNo()))
                .taskIndex(request.getTaskIndex())
                .taskContent(request.getTaskContent())
                .taskDesc(request.getTaskDesc())
                .taskImage(request.getTaskImage())
                .taskFile(request.getTaskFile())
                .build();
        taskRepository.save(taskEntity);

        //System.out.println(challengeRepository.getById(request.getChallengeNo()).getChallengeTaskdeadlines().get(0));
    }

    @Transactional
    public void updateTask(TaskUpdateRequest request){
        TaskEntity taskEntity = taskRepository.findById(request.getTaskNo()).orElse(null);
        TaskEntity requestEntity = new TaskEntity();
        requestEntity = request.toEntity();

        taskEntity.setTaskIndex(requestEntity.getTaskIndex());
        taskEntity.setTaskContent(requestEntity.getTaskContent());
        taskEntity.setTaskDesc(requestEntity.getTaskDesc());
        taskEntity.setTaskImage(request.getTaskImage());
        taskEntity.setTaskFile(request.getTaskFile());
        taskRepository.save(taskEntity);
    }

    @Transactional
    public void deleteTask(int taskNo){
        // int challengeNo = taskRepository.findById(taskNo).orElse(null).getTaskChallengeEntity().getChallengeNo();
        taskRepository.deleteById(taskNo);
        // return challengeNo;
    }

    @Transactional
    public void like(LikeRequest request){
        TaskEntity taskEntity = taskRepository.findById(request.getTaskNo()).orElse(null);
        List<String> list = taskEntity.getTaskLikes();
        list.add(request.getUserEmail());
        taskEntity.setTaskLikes(list);
    }
    @Transactional
    public void unlike(LikeRequest request){
        TaskEntity taskEntity = taskRepository.findById(request.getTaskNo()).orElse(null);
        List<String> list = taskEntity.getTaskLikes();
        list.remove(request.getUserEmail());
        taskEntity.setTaskLikes(list);
    }

    //@Scheduled(cron = "0 * * * * *")
    @Scheduled(cron = "0 0 0 * * *")
    @Transactional
    public void runEveryMidnight(){
        List<ChallengeListResponse> list = challengeService.getChallengelist();
        for(ChallengeListResponse response: list){
            if(response.getChallengeEnddate().isAfter(LocalDate.now().minusDays(2))
                    && response.getChallengeEnddate().isBefore(LocalDate.now())){
                List<GroupmemberEntity> members = groupmemberRepository.findByGroupChallengeEntity_ChallengeNo(response.getChallengeNo());
                for(GroupmemberEntity groupmemberEntity: members){
                    int tasks = taskRepository.findByTaskChallengeEntity_ChallengeNoAndTaskUserEntity_UserEmail(response.getChallengeNo(), groupmemberEntity.getGroupUserEntity().getUserEmail()).size();
                    if(response.getChallengeTaskCnt() == tasks){
                        groupmemberEntity.setGroupIscomplete(true);
                        groupmemberRepository.save(groupmemberEntity);

                        UserEntity userEntity = userRepository.getById(groupmemberEntity.getGroupUserEntity().getUserEmail());
                        Integer temp = userEntity.getUserTotalcomplete();
                        userEntity.setUserTotalcomplete(temp+1);
                        userRepository.save(userEntity);

                        List<FollowEntity> followEntities = followRepository.findByFollowUserEntity_UserEmail(userEntity.getUserEmail());
                        //팔로워들한테 다 save
                        for(FollowEntity followEntity: followEntities){
                            FeedEntity feedEntity = FeedEntity.builder()
                                    .feedType(2)
                                    .feedOwner(followEntity.getFollowFollower())
                                    .feedUserEntity(userEntity)
                                    .feedInfo(Integer.toString(response.getChallengeNo()))
                                    .build();
                            feedRepository.save(feedEntity);
                        }
                    }
                }
            }
        }
    }
}
