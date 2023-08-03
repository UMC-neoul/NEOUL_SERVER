package com.example.neoul.dto.user;


import lombok.*;


@Builder
@NoArgsConstructor
@AllArgsConstructor
@Setter
@Getter
public class TokenRes {
    private String username;
    private boolean firstLogin;
    private String accessToken;
    private String refreshToken;
}
