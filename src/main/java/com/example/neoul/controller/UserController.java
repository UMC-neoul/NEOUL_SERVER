package com.example.neoul.controller;


import com.example.neoul.service.UserService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.io.UnsupportedEncodingException;


@RequiredArgsConstructor
@RestController
@Api(tags={"01.user"})
@RequestMapping("/user")
public class UserController {

    private final UserService userService;


//    @ApiOperation(value = "현재 로그인 유저", notes = "현재 로그인 유저")
//    @GetMapping("/now")
//    public BaseResponse<User> now(){
//
//        if(userService.findNowLoginUser() != null){
//            return BaseResponse.ok(SUCCESS, userService.findNowLoginUser());
//        }
//
//        return BaseResponse.ok(USER_NEED_TO_LOGIN);
//    }


//    @ApiOperation(value = "회원가입", notes = "회원가입")
//    @PostMapping("/signup")
//    public BaseResponse<User> signup(@RequestBody UserReq.SignupUserReq signupUserReq) {
//
//        if(!signupUserReq.isEmailVerified())
//            return BaseResponse.ok(USER_EMAIL_VERIFIED_FALSE);
//
//        return BaseResponse.ok(SUCCESS, userService.signup(signupUserReq));
//    }
//
//
//
//
//    @ApiOperation(value = "로그인", notes = "로그인")
//    @PostMapping("/login")
//    public BaseResponse<TokenRes> signup(@RequestBody UserReq.LoginUserReq loginUserReq) {
//
//        return BaseResponse.ok(SUCCESS, userService.login(loginUserReq));
//    }
//
//
//    @ApiOperation(value = "회원 탈퇴", notes = "회원 탈퇴")
//    @GetMapping("/withdrawal")
//    public BaseResponse<String> withdrawal() {
//        if(userService.withdrawal() != null)
//            return BaseResponse.ok(SUCCESS, userService.withdrawal());
//
//        return BaseResponse.ok(USER_FAILED_TO_WITHDRAWAL);
//    }



}
