package com.example.ssaziptest.domain.user;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import lombok.Setter;

/**
 * 유저 로그인 API ([POST] /api/v1/auth) 요청에 대한 응답값 정의.
 */
@Getter
@Setter
@ApiModel("UserLoginPostResponse")
public class UserLoginPostResponse extends BaseResponseBody{
    @ApiModelProperty(name="JWT 인증 토큰", example="eyJ0eXAiOiJKV1QiLCJhbGciOiJIUzUxMiJ9.eyJzdWIiOiJ0ZXN...")
    String accessToken;

    public static UserLoginPostResponse of(Integer statusCode, String message, String accessToken, UserInfoResponse userInfoResponse) {
        UserLoginPostResponse res = new UserLoginPostResponse();
        res.setStatusCode(statusCode);
        res.setMessage(message);
        res.setAccessToken(accessToken);
        res.setUserInfoResponse(userInfoResponse);
        return res;
    }
}
