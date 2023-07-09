package com.example.neoul.controller;

import com.example.demo.base.BaseException;
import com.example.demo.base.BaseResponse;
import com.example.demo.dto.TokenRes;
import com.example.demo.dto.UserReq;
import com.example.demo.service.AuthService;
import com.example.neoul.dto.TokenRes;
import com.example.neoul.dto.UserReq;
import com.example.neoul.global.entity.BaseEntity;
import com.example.neoul.service.AuthService;
import io.swagger.annotations.ApiOperation;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import static com.example.demo.base.BaseResponseStatus.FAIL;
import static com.example.demo.base.BaseResponseStatus.SUCCESS;

@RestController
@RequestMapping("/oauth")
@RequiredArgsConstructor
public class AuthController {
    private final AuthService authService;


    /**
     * 카카오 소셜로그인
     * https://kauth.kakao.com/oauth/authorize?response_type=code&client_id=6683155e0b612aa910274649f727e377&redirect_uri=http://localhost:8000/oauth/kakao
     * */
    @ApiOperation(value = "인가코드 캐치를 위한 api, 사용x, 백엔드 터미널로 반환중",
            notes = "원래는 프론트엔드가 첫 회원가입 링크로 들어가서 code를 받고, 그 받은 코드로 이 api에 접근해서" +
                    "카카오의 access_token을 반환 후" +
                    "access_token을 아래의 api에 넣어서 우리 사이트의 로그인하고 그 결과를 얻음")
    @GetMapping("/kakao")
    public String getAccessTokenKakao(@RequestParam String code) {
        String accessToken=authService.getKakaoAccessToken(code);
        System.out.println("code for kakaoServer : " + code);
        System.out.println("for /oauth/kakao : " + accessToken);

        return accessToken;
    }

    @ApiOperation(value = "카카오 로그인", notes = "카카오 로그인")
    @PostMapping("/kakao/login")
    public TokenRes kakaoSignupOrLogin(@RequestBody UserReq.SocialReq socialReq) {
        TokenRes tokenRes = authService.createAndLoginKakaoUser(socialReq);

        if(tokenRes == null)
            return null;
        return tokenRes;
    }



}
