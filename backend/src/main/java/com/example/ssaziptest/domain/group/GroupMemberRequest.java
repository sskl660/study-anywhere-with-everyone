package com.example.ssaziptest.domain.group;

import com.example.ssaziptest.domain.challenge.ChallengeEntity;
import com.example.ssaziptest.domain.user.UserEntity;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class GroupMemberRequest {
    //private int groupNo;
    private String groupUsername;
    private UserEntity userEntity;
    private ChallengeEntity challengeEntity;

    public GroupmemberEntity toEntity(){
        GroupmemberEntity groupmemberEntity = GroupmemberEntity.builder()
                .groupUserEntity(userEntity).groupChallengeEntity(challengeEntity)
                .groupUsername(groupUsername)
                .groupIscomplete(false)
                .build();
        return groupmemberEntity;
    }

    @Builder
    public GroupMemberRequest(String groupUsername, UserEntity userEntity, ChallengeEntity challengeEntity) {
        this.groupUsername = groupUsername;
        this.userEntity = userEntity;
        this.challengeEntity = challengeEntity;
    }
}

