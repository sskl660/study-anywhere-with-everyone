package com.example.ssaziptest.domain.task;

import com.example.ssaziptest.repository.ChallengeRepository;
import com.example.ssaziptest.repository.UserRepository;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;

@Data
@NoArgsConstructor
public class TaskUpdateRequest {

    private int taskNo;
    private int taskIndex;
    private String taskContent;
    private String taskDesc;
    private int taskImage;
    private int taskFile;

    public TaskEntity toEntity(){
        TaskEntity taskEntity = TaskEntity.builder()
                .taskNo(taskNo)
                .taskIndex(taskIndex).taskContent(taskContent)
                .taskDesc(taskDesc).taskImage(taskImage)
                .taskFile(taskFile)
                .build();
        return taskEntity;
    }

}
