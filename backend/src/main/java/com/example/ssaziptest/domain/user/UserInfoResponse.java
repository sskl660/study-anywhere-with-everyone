package com.example.ssaziptest.domain.user;

import io.swagger.annotations.ApiModel;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@ApiModel("UserInfoResponse")
public class UserInfoResponse {
    private String userEmail;
    private String userName;
    private int userTerm;


    @Builder
    public UserInfoResponse(String userEmail, String userName, int userTerm) {
        this.userEmail = userEmail;
        this.userName = userName;
        this.userTerm = userTerm;
    }
}
