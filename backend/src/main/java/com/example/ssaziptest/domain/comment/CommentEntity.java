package com.example.ssaziptest.domain.comment;

import com.example.ssaziptest.domain.task.TaskEntity;
import com.example.ssaziptest.domain.user.UserEntity;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.DynamicInsert;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import javax.persistence.*;
import java.time.LocalDateTime;

@Data
@NoArgsConstructor
@DynamicInsert
@Entity(name = "comment")
@Table(name = "comment")
@EntityListeners(AuditingEntityListener.class)
public class CommentEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int commentNo;

    @Column(name = "comment_content", length = 400)
    private String commentContent;

    @CreatedDate
    @Column(name = "comment_date")
    private LocalDateTime commentDate;

    @ManyToOne
    @JoinColumn(name = "fk_user_comment_email")
    private UserEntity commentUserEntity;

    @ManyToOne
    @JoinColumn(name = "fk_task_comment_no")
    private TaskEntity commentTaskEntity;

    @Builder
    public CommentEntity(int commentNo, String commentContent, LocalDateTime commentDate, UserEntity commentUserEntity, TaskEntity commentTaskEntity) {
        this.commentNo = commentNo;
        this.commentContent = commentContent;
        this.commentDate = commentDate;
        this.commentUserEntity = commentUserEntity;
        this.commentTaskEntity = commentTaskEntity;
    }
}
