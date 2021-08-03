package com.example.ssaziptest.domain.user;

import lombok.Builder;
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

    public UserEntity toEntity(){
        UserEntity userEntity = UserEntity.builder()
                .userEmail(userEmail).userName(userName)
                .userPassword(userPassword).userNumber(userNumber)
                .userTerm(userTerm).userGraduated(userGraduated).build();
        return userEntity;
    }

    @Builder
    public UserCreateRequest(String userEmail, String userName, String userPassword, String userNumber, int userTerm, boolean userGraduated) {
        this.userEmail = userEmail;
        this.userName = userName;
        this.userPassword = userPassword;
        this.userNumber = userNumber;
        this.userTerm = userTerm;
        this.userGraduated = userGraduated;
    }
}
