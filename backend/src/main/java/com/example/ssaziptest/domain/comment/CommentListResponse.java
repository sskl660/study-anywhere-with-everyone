package com.example.ssaziptest.domain.comment;

import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
@NoArgsConstructor
public class CommentListResponse {
    private String userEmail;
    private String userName;
    private int userImage;
    private String commentContent;
    private LocalDateTime commentDate;

    @Builder
    public CommentListResponse(String userEmail, String userName, int userImage, String commentContent, LocalDateTime commentDate) {
        this.userEmail = userEmail;
        this.userName = userName;
        this.userImage = userImage;
        this.commentContent = commentContent;
        this.commentDate = commentDate;
    }
}
