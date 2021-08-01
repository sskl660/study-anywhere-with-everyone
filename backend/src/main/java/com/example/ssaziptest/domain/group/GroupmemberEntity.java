package com.example.ssaziptest.domain.group;

import com.example.ssaziptest.domain.challenge.ChallengeEntity;
import com.example.ssaziptest.domain.user.UserEntity;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.DynamicInsert;

import javax.persistence.*;

@Data
@NoArgsConstructor
@DynamicInsert
@Entity(name = "groupmember")
@Table(name = "groupmember")
public class GroupmemberEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int groupNo;
    @Column(name = "group_username")
    private String groupUsername;

    @ManyToOne
    @JoinColumn(name = "fk_user_group_email")
    private UserEntity groupUserEntity;

    @ManyToOne
    @JoinColumn(name = "fk_challenge_group_no")
    private ChallengeEntity groupChallengeEntity;

    @Builder
    public GroupmemberEntity(int groupNo, String groupUsername, UserEntity userEntity, ChallengeEntity challengeEntity) {
        this.groupNo = groupNo;
        this.groupUsername = groupUsername;
        this.groupUserEntity = userEntity;
        this.groupChallengeEntity = challengeEntity;
    }
}
