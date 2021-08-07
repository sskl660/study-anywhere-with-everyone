package com.example.ssaziptest.domain.task;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class ChallengeTicketResponse {
    private int achieveRate;
    private boolean inProgress;

}
