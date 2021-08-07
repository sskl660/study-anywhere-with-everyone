package com.example.ssaziptest.domain.user;

import io.swagger.models.auth.In;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class UserDetailResponse {
    private String userEmail;
    private String userName;
   // private String userPassword;
    private String userNumber;
    private int userTerm;
    private boolean userGraduated;
   // private String userJoindate;
    private Integer userTotalcomplete;
    private Integer userImage;
    private String userGit;
    private String userBlog;
    private String userDevstyle;
    private String userMbti;
    private String userWishfield;
    private String userIntroduce;
    private String userTechstack;
    private Integer userTotaltime;
    private Integer userWeektime;
    private Integer userWeekcomplete;
    private Integer userFollower;
    private Integer userFollowing;

    @Builder
 public UserDetailResponse(String userEmail, String userName, String userNumber, int userTerm, boolean userGraduated, Integer userTotalcomplete, Integer userImage, String userGit, String userBlog, String userDevstyle, String userMbti, String userWishfield, String userIntroduce, String userTechstack, Integer userTotaltime, Integer userWeektime, Integer userWeekcomplete, Integer userFollower, Integer userFollowing) {
  this.userEmail = userEmail;
  this.userName = userName;
  this.userNumber = userNumber;
  this.userTerm = userTerm;
  this.userGraduated = userGraduated;
  this.userTotalcomplete = userTotalcomplete;
  this.userImage = userImage;
  this.userGit = userGit;
  this.userBlog = userBlog;
  this.userDevstyle = userDevstyle;
  this.userMbti = userMbti;
  this.userWishfield = userWishfield;
  this.userIntroduce = userIntroduce;
  this.userTechstack = userTechstack;
  this.userTotaltime = userTotaltime;
  this.userWeektime = userWeektime;
  this.userWeekcomplete = userWeekcomplete;
  this.userFollower = userFollower;
  this.userFollowing = userFollowing;
 }
}
