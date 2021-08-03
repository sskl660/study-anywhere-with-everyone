package com.example.ssaziptest.domain.follow;

import com.example.ssaziptest.domain.user.UserEntity;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Data
@NoArgsConstructor
@Entity(name = "follow")
@Table(name = "follow")
public class FollowEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int followNo;

    @Column(name = "follow_follower")
    private String followFollower;

    @ManyToOne
    @JoinColumn(name = "fk_user_follower_email")
    private UserEntity followUserEntity;

    @Builder
    public FollowEntity(int followNo, String followFollower, UserEntity followUserEntity) {
        this.followNo = followNo;
        this.followFollower = followFollower;
        this.followUserEntity = followUserEntity;
    }
}
