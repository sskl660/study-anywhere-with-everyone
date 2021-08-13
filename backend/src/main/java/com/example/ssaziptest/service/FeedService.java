package com.example.ssaziptest.service;

import com.example.ssaziptest.domain.challenge.ChallengeEntity;
import com.example.ssaziptest.domain.feed.FeedListResponse;
import com.example.ssaziptest.domain.feed.FeedEntity;
import com.example.ssaziptest.domain.feed.GalaxyEntryRequest;
import com.example.ssaziptest.domain.follow.FollowEntity;
import com.example.ssaziptest.domain.user.UserEntity;
import com.example.ssaziptest.repository.*;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.sql.Blob;
import java.util.ArrayList;
import java.util.Arrays;
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
    @Autowired
    private FollowRepository followRepository;

    @Transactional
    public List<FeedListResponse> getChallengeFeeds(String userEmail) throws Exception{
        List<FeedEntity> feedEntities = feedRepository.findByFeedOwner(userEmail);
        List<FeedListResponse> responses = new ArrayList<>();

        for(FeedEntity feedEntity:feedEntities){

            FeedListResponse response = FeedListResponse.builder()
                    .userEmail(feedEntity.getFeedUserEntity().getUserEmail())
                    .userName(feedEntity.getFeedUserEntity().getUserName())
                    .eventtime(feedEntity.getFeedEventtime())
                    .build();
//            Blob userblob = feedEntity.getFeedUserEntity().getUserImage();
//            if(userblob!=null){
//                int bloblength = (int)userblob.length();
//                byte[] blobAsBytes = userblob.getBytes(1,bloblength);
//                userblob.free();
//                response.setUserImage(Arrays.toString(blobAsBytes));
//            }

            switch (feedEntity.getFeedType()){
                //챌린지 가입
                case 1:
                   // System.out.println(feedEntity.getFeedType()+": "+Integer.parseInt(feedEntity.getFeedInfo()));
                    ChallengeEntity challengeEntity = challengeRepository.getById(Integer.parseInt(feedEntity.getFeedInfo()));
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
                    response.setGalaxyComment(feedEntity.getFeedInfo());
                    response.setGalaxyMemberCnt(0);
                    break;
                //팔로잉한 사용자들의 팔로우 활동
                case 4:
                    response.setFeedType(4);
                    UserEntity userEntity = userRepository.findById(feedEntity.getFeedInfo()).orElse(null);
//                    Blob followerblob = userEntity.getUserImage();
//                    if(followerblob!=null){
//                        int bloblength2 = (int)followerblob.length();
//                        byte[] blobAsBytes2 = followerblob.getBytes(1,bloblength2);
//                        followerblob.free();
//
//                        response.setFollowUserImage(Arrays.toString(blobAsBytes2));
//                    }
                    response.setFollowUserEmail(userEntity.getUserEmail());
                    response.setFollowUserName(userEntity.getUserName());
                    response.setFollowerCnt(userEntity.getUserFollower());
                    response.setFollowingCnt(userEntity.getUserFollowing());
                    break;
                //상대방이 나를 팔로우
                case 5:
                    response.setFeedType(5);
                    UserEntity userEntity2 = userRepository.findById(feedEntity.getFeedInfo()).orElse(null);
                    response.setFollowUserEmail(userEntity2.getUserEmail());
                    response.setFollowUserName(userEntity2.getUserName());
                    response.setFollowerCnt(userEntity2.getUserFollower());
                    response.setFollowingCnt(userEntity2.getUserFollowing());
                    break;
            }
            responses.add(response);
        }

        return responses;
    }

    @Transactional
    public void sendGalaxyEntranceMessage(GalaxyEntryRequest request){
        List<FollowEntity> followEntities = followRepository.findByFollowUserEntity_UserEmail(request.getUserEmail());
        //팔로워들한테 다 save
        for(FollowEntity followEntity1: followEntities) {
            FeedEntity feedEntity = FeedEntity.builder()
                    .feedType(3)
                    .feedOwner(followEntity1.getFollowFollower())
                    .feedUserEntity(userRepository.getById(request.getUserEmail()))
                    .feedInfo(request.getMessage())
                    .build();
            feedRepository.save(feedEntity);
        }
    }
}
