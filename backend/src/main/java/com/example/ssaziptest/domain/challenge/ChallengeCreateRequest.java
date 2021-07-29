package com.example.ssaziptest.domain.challenge;

import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class ChallengeCreateRequest {
  //  private int challengeNo;
    private String challengeName;
    private String challengeCategory;
    private int challengeLevel;
    private int challengeCapacity;
    private String challengeStartdate;
    private String challengeEnddate;
    private String challengeDesc;
    private int challengeTaskCnt;

    public ChallengeEntity toEntity(){
        ChallengeEntity challengeEntity = ChallengeEntity.builder()
                .challengeName(challengeName).challengeCategory(challengeCategory)
                .challengeLevel(challengeLevel).challengeCapacity(challengeCapacity).challengeStartdate(challengeStartdate)
                .challengeEnddate(challengeEnddate).challengeDesc(challengeDesc).challengeTaskCnt(challengeTaskCnt).build();
        return challengeEntity;
    }

    @Builder
    public ChallengeCreateRequest(String challengeName, String challengeCategory, int challengeLevel, int challengeCapacity, String challengeStartdate, String challengeEnddate, String challengeDesc, int challengeTaskCnt) {
       // this.challengeNo = challengeNo;
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
