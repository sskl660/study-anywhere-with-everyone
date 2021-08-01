package com.example.ssaziptest.domain.task;

import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class BulletJournalResponse {
    private int taskNo;
    private int taskIndex;
    private String userName;
    private String userEmail;
    //private boolean complete;


    @Builder
    public BulletJournalResponse(int taskNo, int taskIndex, String userName, String userEmail) {
        this.taskNo = taskNo;
        this.taskIndex = taskIndex;
        this.userName = userName;
        this.userEmail = userEmail;
    }
}
