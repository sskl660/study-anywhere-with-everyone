package com.example.ssaziptest.service;

import com.example.ssaziptest.domain.feed.FeedEntity;
import com.example.ssaziptest.domain.follow.FollowEntity;
import com.example.ssaziptest.domain.follow.FollowRequest;
import com.example.ssaziptest.domain.user.UserEntity;
import com.example.ssaziptest.repository.FeedRepository;
import com.example.ssaziptest.repository.FollowRepository;
import com.example.ssaziptest.repository.UserRepository;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.ArrayList;
import java.util.List;

@Service
@AllArgsConstructor
@NoArgsConstructor
public class FollowService {
    @Autowired
    private FollowRepository followRepository;
    @Autowired
    private UserRepository userRepository;
    @Autowired
    private FeedRepository feedRepository;

    @Transactional
    public void followUser(FollowRequest request){
        UserEntity userEntity = userRepository.findById(request.getUserEmail()).orElse(null);
        UserEntity followerEntity = userRepository.findById(request.getFollowFollower()).orElse(null);
        FollowEntity followEntity = FollowEntity.builder()
                .followUserEntity(userEntity)
                .followFollower(request.getFollowFollower())
                .build();
        followRepository.save(followEntity);
        userEntity.setUserFollower(userEntity.getUserFollower()+1);
        followerEntity.setUserFollowing(followerEntity.getUserFollowing()+1);
        userRepository.save(userEntity);
        userRepository.save(followerEntity);

        List<FollowEntity> followEntities = followRepository.findByFollowUserEntity_UserEmail(request.getFollowFollower());
        //팔로워들한테 다 save
        for(FollowEntity followEntity1: followEntities) {
            FeedEntity feedEntity = FeedEntity.builder()
                    .feedType(4)
                    .feedOwner(followEntity1.getFollowFollower())
                    .feedUserEntity(followerEntity)
                    .feedInfo(request.getUserEmail())
                    .build();
            feedRepository.save(feedEntity);
            }
        }
    @Transactional
    public void unfollowUser(FollowRequest request){
        FollowEntity followEntity = followRepository.findByFollowUserEntity_UserEmailAndFollowFollower(request.getUserEmail(), request.getFollowFollower());
        followRepository.delete(followEntity);
        UserEntity userEntity = userRepository.findById(request.getUserEmail()).orElse(null);
        UserEntity followerEntity = userRepository.findById(request.getFollowFollower()).orElse(null);
        userEntity.setUserFollower(userEntity.getUserFollower()-1);
        followerEntity.setUserFollowing(followerEntity.getUserFollowing()-1);
        userRepository.save(userEntity);
        userRepository.save(followerEntity);
    }
    @Transactional
    public List<String[]> getFollowers(String userEmail){
        List<FollowEntity> followEntityList = followRepository.findByFollowUserEntity_UserEmail(userEmail);
        List<String[]> list = new ArrayList<>();
        for(FollowEntity followEntity: followEntityList){
            String[] temp = new String[2];
            temp[0] = followEntity.getFollowFollower();
            temp[1] = userRepository.findById(followEntity.getFollowFollower()).orElse(null).getUserName();
            list.add(temp);
        }
        return list;
    }
    @Transactional
    public List<String[]> getFollowings(String userEmail){
        List<FollowEntity> followEntityList = followRepository.findByFollowFollower(userEmail);
        List<String[]> list = new ArrayList<>();
        for(FollowEntity followEntity: followEntityList){
            String[] temp = new String[2];
            temp[0] = followEntity.getFollowUserEntity().getUserEmail();
            temp[1] = userRepository.findById(followEntity.getFollowUserEntity().getUserEmail()).orElse(null).getUserName();
            list.add(temp);
        }
        return list;
    }
}
