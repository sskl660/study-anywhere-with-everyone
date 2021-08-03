package com.example.ssaziptest.domain.challenge;

import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@NoArgsConstructor
public class ChallengeDetailResponse {
    private int challengeNo;
    private String challengeName;
    private String challengeCategory;
    private int challengeLevel;
    private int challengeCapacity;
    private String challengeStartdate;
    private String challengeEnddate;
    private String challengeDesc;
    private int challengeTaskCnt;
    //private String[][] challengeGroup;
    private List<String> challengeTaskdeadlines;
    private List<String[]> challengeGroup;

    public ChallengeEntity toEntity(){
        ChallengeEntity challengeEntity = ChallengeEntity.builder()
                .challengeNo(challengeNo)
                .challengeName(challengeName).challengeCategory(challengeCategory)
                .challengeLevel(challengeLevel).challengeCapacity(challengeCapacity).challengeStartdate(challengeStartdate)
                .challengeEnddate(challengeEnddate).challengeDesc(challengeDesc).challengeTaskCnt(challengeTaskCnt)
                .challengeTaskdeadlines(challengeTaskdeadlines).build();
        return challengeEntity;
    }

    @Builder

    public ChallengeDetailResponse(int challengeNo, String challengeName, String challengeCategory, int challengeLevel, int challengeCapacity, String challengeStartdate, String challengeEnddate, String challengeDesc, int challengeTaskCnt, List<String> challengeTaskdeadlines) {
        this.challengeNo = challengeNo;
        this.challengeName = challengeName;
        this.challengeCategory = challengeCategory;
        this.challengeLevel = challengeLevel;
        this.challengeCapacity = challengeCapacity;
        this.challengeStartdate = challengeStartdate;
        this.challengeEnddate = challengeEnddate;
        this.challengeDesc = challengeDesc;
        this.challengeTaskCnt = challengeTaskCnt;
        this.challengeTaskdeadlines = challengeTaskdeadlines;
    }
}
