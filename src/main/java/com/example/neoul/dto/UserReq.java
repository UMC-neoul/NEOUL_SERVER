package com.example.neoul.dto;

import io.swagger.annotations.ApiModelProperty;
import lombok.*;

public class UserReq {

    @Builder
    @NoArgsConstructor
    @AllArgsConstructor
    @Setter
    @Getter
    public static class LoginUserReq {
        @ApiModelProperty(example = "test1@naver.com")
        private String username;
        @ApiModelProperty(example = "test1234")
        private String password;
    }

    @Builder
    @NoArgsConstructor
    @AllArgsConstructor
    @Setter
    @Getter
    public static class SocialLoginUserReq {
        @ApiModelProperty(example = "test@gmail.com")
        private String username;
        @ApiModelProperty(example = "kakao")
        private String social;
    }




    @Builder
    @NoArgsConstructor
    @AllArgsConstructor
    @Setter
    @Getter
    public static class SignupUserReq {
        private String username;
        private String password;
        private String name;
        private String imageUrl;

        @ApiModelProperty(example = "true or false")
        private boolean emailVerified;
    }
    

    @Builder
    @NoArgsConstructor
    @AllArgsConstructor
    @Setter
    @Getter
    public static class EmailAuthReq {
        private String email;
    }




    @Builder
    @NoArgsConstructor
    @AllArgsConstructor
    @Setter
    @Getter
    public static class SocialReq {
        private String social;
        private String accessTokenFromSocial;
    }


    @Builder
    @NoArgsConstructor
    @AllArgsConstructor
    @Setter
    @Getter
    public static class ModifyUserReq {
        private String imgUrl; //이미지
    }



}
