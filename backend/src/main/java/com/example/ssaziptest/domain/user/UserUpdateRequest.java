package com.example.ssaziptest.domain.user;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class UserUpdateRequest {
    private String userEmail;

    private int userImage;
    private String userGit;
    private String userBlog;
    private String userDevstyle;
    private String userMbti;
    private String userWishfield;
    private String userIntroduce;
    private String userTechstack;

}
