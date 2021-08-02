package com.example.ssaziptest.domain.follow;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class FollowRequest {
    private String userEmail;
    private String followFollower;

//    public FollowEntity toEntity(){
//
//        FollowEntity followEntity = FollowEntity.builder()
//
//                .build();
//    }
}
