package com.example.ssaziptest.domain.comment;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class CommentRequest {
    private int taskNo;
    private String userEmail;
    private String commentContent;

}
