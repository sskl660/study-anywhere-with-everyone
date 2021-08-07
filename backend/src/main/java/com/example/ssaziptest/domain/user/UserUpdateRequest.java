package com.example.ssaziptest.domain.user;

import lombok.Data;
import lombok.NoArgsConstructor;

import java.sql.Blob;

@Data
@NoArgsConstructor
public class UserUpdateRequest {
    private String userEmail;

    private String userGit;
    private String userBlog;
    private String userDevstyle;
    private String userMbti;
    private String userWishfield;
    private String userIntroduce;
    private String userTechstack;

}
