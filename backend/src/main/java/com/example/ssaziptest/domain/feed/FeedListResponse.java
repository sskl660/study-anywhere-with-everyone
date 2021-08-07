package com.example.ssaziptest.domain.feed;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.sql.Blob;
import java.time.LocalDate;
import java.time.LocalDateTime;

@Data
@NoArgsConstructor
public class FeedListResponse {
    /*소식 종류*/
    private int feedType;

    /*이벤트 발생 유저 정보*/
    private String userEmail;
    private String userName;
    private int userImage;

    /*챌린지 가입or완주 소식 정보*/
    private int challengeNo;
    private String challengeName;
    private String challengeDesc;
    private LocalDate challengeStartdate;
    private LocalDate challengeEnddate;
    private int challengeMemberCnt;
    /*랜각공 참여 정보*/
    private String galaxyComment;
    private int galaxyMemberCnt;
    /*팔로우 소식 정보*/
    private String followUserEmail;
    private int followUserImage;
    private int followerCnt;
    private int followingCnt;
    /*이벤트 발생 시각*/
    private LocalDateTime eventtime;

    @Builder
    public FeedListResponse(String userEmail, String userName, int userImage, int challengeNo, String challengeName, String challengeDesc, LocalDate challengeStartdate, LocalDate challengeEnddate, int challengeMemberCnt, String galaxyComment, int galaxyMemberCnt, String followUserEmail, int followUserImage, int followerCnt, int followingCnt, LocalDateTime eventtime) {
        this.userEmail = userEmail;
        this.userName = userName;
        this.userImage = userImage;
        this.challengeNo = challengeNo;
        this.challengeName = challengeName;
        this.challengeDesc = challengeDesc;
        this.challengeStartdate = challengeStartdate;
        this.challengeEnddate = challengeEnddate;
        this.challengeMemberCnt = challengeMemberCnt;
        this.galaxyComment = galaxyComment;
        this.galaxyMemberCnt = galaxyMemberCnt;
        this.followUserEmail = followUserEmail;
        this.followUserImage = followUserImage;
        this.followerCnt = followerCnt;
        this.followingCnt = followingCnt;
        this.eventtime = eventtime;
    }
}
