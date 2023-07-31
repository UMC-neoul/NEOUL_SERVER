package com.example.neoul.controller;


import com.example.neoul.dto.user.TokenRes;
import com.example.neoul.dto.user.UserReq;
import com.example.neoul.dto.user.UserRes;
import com.example.neoul.entity.user.User;
import com.example.neoul.global.entity.ApiResponse;
import com.example.neoul.global.exception.BadRequestException;
import com.example.neoul.service.UserService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;


@RequiredArgsConstructor
@RestController
@Api(tags={"01.user"})
@RequestMapping("/user")
public class UserController {

    private final UserService userService;


    @ApiOperation(value = "현재 로그인 유저", notes = "현재 로그인 유저")
    @GetMapping("/now")
    public ApiResponse<User> now(){
        return new ApiResponse<>(userService.findNowLoginUser());
    }


    @ApiOperation(value = "회원가입", notes = "회원가입")
    @PostMapping("/signup")
    public ApiResponse<UserRes.UserDetailDto> signup(@RequestBody UserReq.SignupUserDto signupUserDto) {
        //이메일 형식 체크
        if(userService.validationEmail(signupUserDto.getUsername()))
            throw new BadRequestException("이메일 형식으로 입력해주세요");

        //비밀번호 형식 체크
        if(signupUserDto.getPassword().length() < 6)
            throw new BadRequestException("비밀번호를 6자리 이상 입력해주세요");


        return new ApiResponse<>(userService.signup(signupUserDto));
    }




    @ApiOperation(value = "로그인", notes = "로그인")
    @PostMapping("/login")
    public ApiResponse<TokenRes> signup(@RequestBody UserReq.LoginUserDto loginUserDto) {
        return new ApiResponse<>(userService.login(loginUserDto));
    }

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
