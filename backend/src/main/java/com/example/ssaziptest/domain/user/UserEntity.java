package com.example.ssaziptest.domain.user;

import com.example.ssaziptest.domain.comment.CommentEntity;
import com.example.ssaziptest.domain.feed.FeedEntity;
import com.example.ssaziptest.domain.group.GroupmemberEntity;
import com.example.ssaziptest.domain.task.TaskEntity;

import com.sun.istack.NotNull;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.DynamicInsert;
import org.hibernate.annotations.DynamicUpdate;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import javax.persistence.*;
import java.sql.Blob;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Data
@NoArgsConstructor
@DynamicInsert
@DynamicUpdate
@Entity(name = "user")
@Table(name = "user")
@EntityListeners(AuditingEntityListener.class)
public class UserEntity {

    //not null

    @Id
    @NotNull
    private String userEmail;
    @Column(name = "user_name")
    @NotNull
    private String userName;
    @Column(name = "user_password")
    @NotNull
    private String userPassword;
    @Column(name = "user_number")
    @NotNull
    private String userNumber;
    @Column(name = "user_term")
    @NotNull
    private int userTerm;
    @Column(name = "user_graduated")
    @NotNull
    private Boolean userGraduated; //boolean으로 하면 lombok 안먹음;
    @CreatedDate
    @Column(name = "user_joindate")
    private LocalDateTime userJoindate;

    //초기 null
    @Column(name = "user_totalcomplete")
    private Integer userTotalcomplete;
    @Lob
    @Column(name = "user_image", columnDefinition = "")
    private Blob userImage;
    @Column(name = "user_git", length = 20971520)
    private String userGit;
    @Column(name = "user_blog")
    private String userBlog;
    @Column(name = "user_devstyle")
    private String userDevstyle;
    @Column(name = "user_mbti")
    private String userMbti;
    @Column(name = "user_wishfield")
    private String userWishfield;
    @Column(name = "user_introduce")
    private String userIntroduce;
    @Column(name = "user_techstack")
    private String userTechstack;
    @Column(name = "user_totaltime")
    private Integer userTotaltime;
    @Column(name = "user_weektime")
    private Integer userWeektime;
    @Column(name = "user_weekcomplete")
    private Integer userWeekcomplete;
    @Column(name = "user_follower")
    private Integer userFollower;
    @Column(name = "user_following")
    private Integer userFollowing;

    @OneToMany(mappedBy = "groupUserEntity")
    List<GroupmemberEntity> challengeGroups = new ArrayList<>();
    @OneToMany(mappedBy = "taskUserEntity")
    List<TaskEntity> taskResults = new ArrayList<>();
    @OneToMany(mappedBy = "feedUserEntity")
    List<FeedEntity> feedList = new ArrayList<>();
    @OneToMany(mappedBy = "commentUserEntity")
    List<CommentEntity> commentEntities = new ArrayList<>();


    @PrePersist
    public void prePersist(){
        //blob null주의..
        this.userTotalcomplete=this.userTotalcomplete==null?0:this.userTotalcomplete;
        this.userImage=this.userImage==null?null:this.userImage;
        this.userGit=this.userGit==null?"":this.userGit;
        this.userBlog=this.userBlog==null?"":this.userBlog;
        this.userDevstyle=this.userDevstyle==null?"":this.userDevstyle;
        this.userMbti=this.userMbti==null?"":this.userMbti;
        this.userWishfield=this.userWishfield==null?"":this.userWishfield;
        this.userIntroduce=this.userIntroduce==null?"":this.userIntroduce;
        this.userTechstack=this.userTechstack==null?"":this.userTechstack;
        this.userTotaltime=this.userTotaltime==null?0:this.userTotaltime;
        this.userWeektime=this.userWeektime==null?0:this.userWeektime;
        this.userWeekcomplete=this.userWeekcomplete==null?0:this.userWeekcomplete;
        this.userFollower=this.userFollower==null?0:this.userFollower;
        this.userFollowing=this.userFollowing==null?0:this.userFollowing;
        //this.challengegroups=this.challengegroups.isEmpty()?
    }

//    @Builder
//    public UserEntity(String userEmail, String userName, String userPassword, String userNumber, int userTerm, boolean userGraduated) {
//        this.userEmail = userEmail;
//        this.userName = userName;
//        this.userPassword = userPassword;
//        this.userNumber = userNumber;
//        this.userTerm = userTerm;
//        this.userGraduated = userGraduated;
//    }

    @Builder
    public UserEntity(String userEmail, String userName, String userPassword, String userNumber, int userTerm, boolean userGraduated, LocalDateTime userJoindate, Integer userTotalcomplete, Blob userImage, String userGit, String userBlog, String userDevstyle, String userMbti, String userWishfield, String userIntroduce, String userTechstack, int userTotaltime, int userWeektime, int userWeekcomplete, int userFollower, int userFollowing) {
        this.userEmail = userEmail;
        this.userName = userName;
        this.userPassword = userPassword;
        this.userNumber = userNumber;
        this.userTerm = userTerm;
        this.userGraduated = userGraduated;
        this.userJoindate = userJoindate;
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
