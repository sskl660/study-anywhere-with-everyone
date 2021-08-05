package com.example.ssaziptest.domain.challenge;

import com.example.ssaziptest.domain.group.GroupmemberEntity;
import com.example.ssaziptest.domain.task.TaskEntity;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.DynamicInsert;

import javax.persistence.*;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

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
    private LocalDate challengeStartdate;
    @Column(name = "challenge_enddate")
    private LocalDate challengeEnddate;
    @Column(name = "challenge_desc", length = 600)
    private String challengeDesc;
    @Column(name = "challenge_task_cnt")
    private int challengeTaskCnt;

    //task생성할때 task 갯수 뿐 아니라 각각의 마감 기한이 여기서 필요할거같은데...?
    @ElementCollection
    @Column(name = "challenge_taskdeadlines")
    private List<LocalDate> challengeTaskdeadlines;

    @OneToMany(mappedBy = "groupChallengeEntity")
    List<GroupmemberEntity> members = new ArrayList<>();
    @OneToMany(mappedBy = "taskChallengeEntity")
    List<TaskEntity> tasks = new ArrayList<>();

    @Builder
    public ChallengeEntity(int challengeNo, String challengeName, String challengeCategory, int challengeLevel, int challengeCapacity, LocalDate challengeStartdate, LocalDate challengeEnddate, String challengeDesc, int challengeTaskCnt, List<LocalDate> challengeTaskdeadlines, List<GroupmemberEntity> members, List<TaskEntity> tasks) {
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
        this.members = members;
        this.tasks = tasks;
    }
}
