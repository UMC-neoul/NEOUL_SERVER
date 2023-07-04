package com.example.neoul.dto;

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
    public static class EmailAuthRes {
        int checkCode;
    }



}
