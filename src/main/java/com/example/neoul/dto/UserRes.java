package com.example.neoul.dto;

import com.example.neoul.entity.user.User;
import lombok.*;

public class UserRes {


    @Builder
    @NoArgsConstructor
    @AllArgsConstructor
    @Setter
    @Getter
    public static class SignupUserRes {
        private String username;
        private String name;
    }


    @Builder
    @NoArgsConstructor
    @AllArgsConstructor
    @Setter
    @Getter
    public static class UserDetailDto {
        private String username;
        private String name;
        private String phone;
        private String imageUrl;
        private String social;
        private boolean firstLogin;

        public static UserDetailDto toDto(User user){
            return UserDetailDto.builder()
                    .username(user.getUsername())
                    .name(user.getName())
                    .phone(user.getPhone())
                    .imageUrl(user.getImageUrl())
                    .social(user.getSocial())
                    .firstLogin(user.isFirstLogin())
                    .build();
        }
    }



    @Builder
    @NoArgsConstructor
    @AllArgsConstructor
    @Setter
    @Getter
    public static class EmailAuthRes {
        int checkCode;
    }



}
