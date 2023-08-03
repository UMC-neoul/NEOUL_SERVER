package com.example.neoul.dto.user;

import io.swagger.annotations.ApiModelProperty;
import lombok.*;

public class UserReq {

    @Builder
    @NoArgsConstructor
    @AllArgsConstructor
    @Setter
    @Getter
    public static class LoginUserDto {
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
    public static class SocialLoginUserDto {
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
    public static class SignupUserDto {
        private String username;
        private String password;
        private String name;
        private String phone;
        private String birth;
        private String imageUrl;
    }
    

    @Builder
    @NoArgsConstructor
    @AllArgsConstructor
    @Setter
    @Getter
    public static class EmailAuthDto {
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

    @Builder
    @NoArgsConstructor
    @AllArgsConstructor
    @Setter
    @Getter
    public static class UserInfoEditReq {
        private String username;
        private String birth;
        private String phone;
    }

}
