package com.example.ssaziptest.domain.task;

import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class BulletJournalResponse {

    private String userName;
    private String userEmail;
    private Integer[] taskNo;
    //private boolean complete;

    @Builder
    public BulletJournalResponse(String userName, String userEmail, Integer[] taskNo) {
        this.userName = userName;
        this.userEmail = userEmail;
        this.taskNo = taskNo;
    }
}
