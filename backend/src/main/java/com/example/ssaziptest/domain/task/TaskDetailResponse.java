package com.example.ssaziptest.domain.task;

import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class TaskDetailResponse {
    private int taskNo;
    private int taskIndex;
    private String taskContent;
    private String taskDesc;
    private String taskImage;
    private String taskFile;
    private String userEmail;
    private String userName;

    @Builder
    public TaskDetailResponse(int taskNo, int taskIndex, String taskContent, String taskDesc, String taskImage, String taskFile, String userEmail, String userName) {
        this.taskNo = taskNo;
        this.taskIndex = taskIndex;
        this.taskContent = taskContent;
        this.taskDesc = taskDesc;
        this.taskImage = taskImage;
        this.taskFile = taskFile;
        this.userEmail = userEmail;
        this.userName = userName;
    }
}
