package com.example.ssaziptest.domain.task;

import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@NoArgsConstructor
public class TaskDetailResponse {
    private int taskNo;
    private int taskIndex;
    private String taskContent;
    private String taskDesc;
    private String taskImage;
    private String taskFile;
    private String taskFileName;
    private String taskFileType;
    private String userEmail;
    private String userName;
    private int userTerm;
    private int likes;
    private List<String> likemembers;

    @Builder
    public TaskDetailResponse(int taskNo, int taskIndex, String taskContent, String taskDesc, String taskImage, String taskFile, String taskFileName, String taskFileType, String userEmail, String userName, int userTerm, int likes, List<String> likemembers) {
        this.taskNo = taskNo;
        this.taskIndex = taskIndex;
        this.taskContent = taskContent;
        this.taskDesc = taskDesc;
        this.taskImage = taskImage;
        this.taskFile = taskFile;
        this.taskFileName = taskFileName;
        this.taskFileType = taskFileType;
        this.userEmail = userEmail;
        this.userName = userName;
        this.userTerm = userTerm;
        this.likes = likes;
        this.likemembers = likemembers;
    }
}
