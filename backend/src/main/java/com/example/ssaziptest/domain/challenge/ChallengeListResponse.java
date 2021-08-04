package com.example.ssaziptest.domain.challenge;

import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
import java.time.LocalDateTime;

@Data
@NoArgsConstructor
public class ChallengeListResponse {
    private int challengeNo;
    private String challengeName;
    private String challengeCategory;
    private int challengeLevel;
    private int challengeCapacity;
    private LocalDate challengeStartdate;
    private LocalDate challengeEnddate;
    private String challengeDesc;
    private int challengeTaskCnt;

    public ChallengeEntity toEntity(){
        ChallengeEntity challengeEntity = ChallengeEntity.builder()
                .challengeNo(challengeNo)
                .challengeName(challengeName).challengeCategory(challengeCategory)
                .challengeLevel(challengeLevel).challengeCapacity(challengeCapacity).challengeStartdate(challengeStartdate)
                .challengeEnddate(challengeEnddate).challengeDesc(challengeDesc).challengeTaskCnt(challengeTaskCnt).build();
        return challengeEntity;
    }

    @Builder
    public ChallengeListResponse(int challengeNo, String challengeName, String challengeCategory, int challengeLevel, int challengeCapacity, LocalDate challengeStartdate, LocalDate challengeEnddate, String challengeDesc, int challengeTaskCnt) {
        this.challengeNo = challengeNo;
        this.challengeName = challengeName;
        this.challengeCategory = challengeCategory;
        this.challengeLevel = challengeLevel;
        this.challengeCapacity = challengeCapacity;
        this.challengeStartdate = challengeStartdate;
        this.challengeEnddate = challengeEnddate;
        this.challengeDesc = challengeDesc;
        this.challengeTaskCnt = challengeTaskCnt;
    }
}
