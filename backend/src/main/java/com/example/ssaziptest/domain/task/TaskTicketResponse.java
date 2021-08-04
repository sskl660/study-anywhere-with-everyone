package com.example.ssaziptest.domain.task;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class TaskTicketResponse {
    private int challengeNo;
    private int challengeTaskCnt;
    private Integer[] taskNo;
    private Boolean isComplete;
}
