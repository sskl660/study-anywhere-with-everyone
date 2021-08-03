package com.example.ssaziptest.service;

import com.example.ssaziptest.domain.challenge.ChallengeEntity;
import com.example.ssaziptest.domain.feed.FeedListResponse;
import com.example.ssaziptest.domain.feed.FeedEntity;
import com.example.ssaziptest.domain.user.UserEntity;
import com.example.ssaziptest.repository.ChallengeRepository;
import com.example.ssaziptest.repository.FeedRepository;
import com.example.ssaziptest.repository.GroupmemberRepository;
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
public class FeedService {

    @Autowired
    private FeedRepository feedRepository;
    @Autowired
    private ChallengeRepository challengeRepository;
    @Autowired
    private GroupmemberRepository groupmemberRepository;
    @Autowired
    private UserRepository userRepository;

    @Transactional
    public List<FeedListResponse> getChallengeFeeds(String userEmail){
        List<FeedEntity> feedEntities = feedRepository.findByFeedOwner(userEmail);
        List<FeedListResponse> responses = new ArrayList<>();

        for(FeedEntity feedEntity:feedEntities){
            FeedListResponse response = FeedListResponse.builder()
                    .userEmail(feedEntity.getFeedUserEntity().getUserEmail())
                    .userName(feedEntity.getFeedUserEntity().getUserName())
                    .userImage(feedEntity.getFeedUserEntity().getUserImage())
                    .eventtime(feedEntity.getFeedEventtime())
                    .build();

            switch (feedEntity.getFeedType()){
                //챌린지 가입
                case 1:
                    ChallengeEntity challengeEntity = challengeRepository.findById(Integer.parseInt(feedEntity.getFeedInfo())).orElse(null);
                    response.setFeedType(1);
                    response.setChallengeNo(Integer.parseInt(feedEntity.getFeedInfo()));
                    response.setChallengeName(challengeEntity.getChallengeName());
                    response.setChallengeDesc(challengeEntity.getChallengeDesc());
                    response.setChallengeStartdate(challengeEntity.getChallengeStartdate());
                    response.setChallengeEnddate(challengeEntity.getChallengeEnddate());
                    //개선점 찾기
                    response.setChallengeMemberCnt(groupmemberRepository.findByGroupChallengeEntity_ChallengeNo(Integer.parseInt(feedEntity.getFeedInfo())).size());
                    break;
                //챌린지 완주
                case 2:
                    ChallengeEntity challengeEntity2 = challengeRepository.findById(Integer.parseInt(feedEntity.getFeedInfo())).orElse(null);
                    response.setFeedType(2);
                    response.setChallengeNo(Integer.parseInt(feedEntity.getFeedInfo()));
                    response.setChallengeName(challengeEntity2.getChallengeName());
                    response.setChallengeDesc(challengeEntity2.getChallengeDesc());
                    response.setChallengeStartdate(challengeEntity2.getChallengeStartdate());
                    response.setChallengeEnddate(challengeEntity2.getChallengeEnddate());
                    //개선점 찾기
                    response.setChallengeMemberCnt(groupmemberRepository.findByGroupChallengeEntity_ChallengeNo(Integer.parseInt(feedEntity.getFeedInfo())).size());
                    break;
                //랜각공 참여
                case 3:
                    //갤럭시 구현 후 결정
                    response.setFeedType(3);
                    response.setGalaxyComment("");
                    response.setGalaxyMemberCnt(0);
                    break;
                //팔로우
                case 4:
                    response.setFeedType(4);
                    UserEntity userEntity = userRepository.findById(feedEntity.getFeedInfo()).orElse(null);
                    response.setFollowUserEmail(userEntity.getUserEmail());
                    response.setFollowUserImage(userEntity.getUserImage());
                    response.setFollowerCnt(userEntity.getUserFollower());
                    response.setFollowingCnt(userEntity.getUserFollowing());
                    break;
            }
            responses.add(response);
        }

        return responses;
    }
}