package com.example.ssaziptest.service;

import com.example.ssaziptest.domain.task.TaskEntity;
import com.example.ssaziptest.domain.task.TaskSubmitRequest;
import com.example.ssaziptest.domain.task.TaskUpdateRequest;
import com.example.ssaziptest.repository.ChallengeRepository;
import com.example.ssaziptest.repository.TaskRepository;
import com.example.ssaziptest.repository.UserRepository;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.config.Task;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;

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


    //잠깐 뇌절. 그럼 챌린지 생성할때 과제들... 과제들 deadline은..?
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
}
