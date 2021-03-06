package com.example.ssaziptest.service;

import com.example.ssaziptest.domain.challenge.ChallengeListResponse;
import com.example.ssaziptest.domain.feed.FeedEntity;
import com.example.ssaziptest.domain.follow.FollowEntity;
import com.example.ssaziptest.domain.group.GroupmemberEntity;
import com.example.ssaziptest.domain.task.*;
import com.example.ssaziptest.domain.user.UserEntity;
import com.example.ssaziptest.repository.*;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.scheduling.config.Task;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import javax.sql.rowset.serial.SerialBlob;
import javax.transaction.Transactional;
import java.sql.Blob;
import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

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

/*
    @Transactional
    public void submitTask(Blob img,Blob file, TaskSubmitRequest request) throws Exception{

        TaskEntity taskEntity = TaskEntity.builder()
                .taskUserEntity(userRepository.getById(request.getUserEmail()))
                .taskChallengeEntity(challengeRepository.getById(request.getChallengeNo()))
                .taskIndex(request.getTaskIndex())
                .taskContent(request.getTaskContent())
                .taskDesc(request.getTaskDesc())
                .taskImage(img)
                .taskFile(file)
                .build();

        taskRepository.save(taskEntity);

        //System.out.println(challengeRepository.getById(request.getChallengeNo()).getChallengeTaskdeadlines().get(0));
    }

 */

    @Transactional
    public void updateTask(TaskUpdateRequest request){
        TaskEntity taskEntity = taskRepository.findById(request.getTaskNo()).orElse(null);
        TaskEntity requestEntity = new TaskEntity();
        requestEntity = request.toEntity();

        taskEntity.setTaskIndex(requestEntity.getTaskIndex());
        taskEntity.setTaskContent(requestEntity.getTaskContent());
        taskEntity.setTaskDesc(requestEntity.getTaskDesc());
//        taskEntity.setTaskImage(request.getTaskImage());
//        taskEntity.setTaskFile(request.getTaskFile());
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
    @Transactional
    public TaskLikeCheckResponse likeCheck(String user_email, int task_no){
        Optional<TaskEntity> taskEntity = taskRepository.findById(task_no);
        List<String> list = taskEntity.get().getTaskLikes();
        TaskLikeCheckResponse taskLikeCheckResponse =new TaskLikeCheckResponse();
        if(list.contains(user_email)){//????????? true ??????
            taskLikeCheckResponse.setUserLikeFlag(true);
            return taskLikeCheckResponse;
        }
        else {
            taskLikeCheckResponse.setUserLikeFlag(false);
            return taskLikeCheckResponse;
        }
    }

//    @Scheduled(cron = "0 4 0 * * *")
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
                        userEntity.setUserWeekcomplete(userEntity.getUserWeekcomplete()+1);
                        userRepository.save(userEntity);

                        List<FollowEntity> followEntities = followRepository.findByFollowUserEntity_UserEmail(userEntity.getUserEmail());
                        //?????????????????? ??? save
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
