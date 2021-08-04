package com.example.ssaziptest.domain.task;

import com.example.ssaziptest.domain.challenge.ChallengeEntity;
import com.example.ssaziptest.domain.comment.CommentEntity;
import com.example.ssaziptest.domain.user.UserEntity;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.DynamicInsert;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Data
@NoArgsConstructor
@DynamicInsert
@Entity(name = "task")
@Table(name = "task")
public class TaskEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int taskNo;

    @ManyToOne
    @JoinColumn(name = "fk_user_task_email")
    private UserEntity taskUserEntity;

    @ManyToOne
    @JoinColumn(name = "fk_challenge_task_no")
    private ChallengeEntity taskChallengeEntity;

    @Column(name = "task_index")
    private int taskIndex;
    @Column(name = "task_content")
    private String taskContent;
    @Column(name = "task_desc")
    private String taskDesc;
    @Column(name = "task_image")
    private String taskImage;
    @Column(name = "task_file")
    private String taskFile;
    @ElementCollection
    @Column(name = "task_ikes")
    private List<String> taskLikes;
//    @Column(name = "task_deadline")
//    private String taskDeadline;

    @OneToMany(mappedBy = "commentTaskEntity")
    List<CommentEntity> commentEntities = new ArrayList<>();

    @Builder
    public TaskEntity(int taskNo, UserEntity taskUserEntity, ChallengeEntity taskChallengeEntity, int taskIndex, String taskContent, String taskDesc, String taskImage, String taskFile, List<String> taskLikes, List<CommentEntity> commentEntities) {
        this.taskNo = taskNo;
        this.taskUserEntity = taskUserEntity;
        this.taskChallengeEntity = taskChallengeEntity;
        this.taskIndex = taskIndex;
        this.taskContent = taskContent;
        this.taskDesc = taskDesc;
        this.taskImage = taskImage;
        this.taskFile = taskFile;
        this.taskLikes = taskLikes;
        this.commentEntities = commentEntities;
    }
    @PrePersist
    public  void taskprePersist(){
        this.taskContent=this.taskContent==null?"":this.taskContent;
        this.taskImage=this.taskImage==null?"":this.taskImage;
        this.taskFile=this.taskFile==null?"":this.taskFile;
        this.taskLikes=this.taskLikes==null?new ArrayList<>():this.taskLikes;
    }
}
