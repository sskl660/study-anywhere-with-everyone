package com.example.ssaziptest.domain.challenge;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.DynamicInsert;

import javax.persistence.*;

@Data
@NoArgsConstructor
@DynamicInsert
@Entity(name = "challenge")
@Table(name = "challenge")
public class ChallengeEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int challengeNo;
    @Column(name = "challenge_name")
    private String challengeName;
    @Column(name = "challenge_category")
    private String challengeCategory;
    @Column(name = "challenge_level")
    private int challengeLevel;
    @Column(name = "challenge_capacity")
    private int challengeCapacity;
    @Column(name = "challenge_startdate")
    private String challengeStartdate;
    @Column(name = "challenge_enddate")
    private String challengeEnddate;
    @Column(name = "challenge_desc")
    private String challengeDesc;
    @Column(name = "challenge_task_cnt")
    private int challengeTaskCnt;

    @Builder
    public ChallengeEntity(int challengeNo, String challengeName, String challengeCategory, int challengeLevel, int challengeCapacity, String challengeStartdate, String challengeEnddate, String challengeDesc, int challengeTaskCnt) {
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
