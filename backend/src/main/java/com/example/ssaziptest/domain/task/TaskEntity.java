package com.example.ssaziptest.domain.task;

import com.example.ssaziptest.domain.challenge.ChallengeEntity;
import com.example.ssaziptest.domain.user.UserEntity;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.DynamicInsert;

import javax.persistence.*;

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
//    @Column(name = "task_deadline")
//    private String taskDeadline;

    @Builder
    public TaskEntity(int taskNo, UserEntity taskUserEntity, ChallengeEntity taskChallengeEntity, int taskIndex, String taskContent, String taskDesc, String taskImage, String taskFile) {
        this.taskNo = taskNo;
        this.taskUserEntity = taskUserEntity;
        this.taskChallengeEntity = taskChallengeEntity;
        this.taskIndex = taskIndex;
        this.taskContent = taskContent;
        this.taskDesc = taskDesc;
        this.taskImage = taskImage;
        this.taskFile = taskFile;
        //this.taskDeadline = taskDeadline;
    }

    @PrePersist
    public  void taskprePersist(){
        this.taskContent=this.taskContent==null?"":this.taskContent;
        this.taskImage=this.taskImage==null?"":this.taskImage;
        this.taskFile=this.taskFile==null?"":this.taskFile;
    }
}
