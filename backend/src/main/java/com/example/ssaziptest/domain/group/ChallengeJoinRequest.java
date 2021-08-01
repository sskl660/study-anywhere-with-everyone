package com.example.ssaziptest.domain.group;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class ChallengeJoinRequest {
    private String userEmail;
    private int challengeNo;

}
