package com.example.ssaziptest.domain.user;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class UserCreateRequest {

    private String userEmail;
    private String userName;
    private String userPassword;
    private String userNumber;
    private int userTerm;
    private boolean userGraduated;
    private String userJoindate;
}
