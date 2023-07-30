package com.example.neoul.controller;

import com.example.neoul.global.entity.ApiResponse;
import com.example.neoul.global.exception.BadRequestException;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@Slf4j
@RestController
@Api(tags={"00.test"})
@RequestMapping("/test")
public class TestController {
    @GetMapping("/testapi")
    public ResponseEntity test(){
        log.info("test api 수신");
        return new ResponseEntity("test ok",HttpStatus.OK);
    }


    @ApiOperation(value = "API 응답 반환 형식 예", notes = "API 응답 반환 형식 예")
    @GetMapping("")
    public ApiResponse<String> test2(@RequestParam(required = false) String s){

        if("null".equals(s))
            throw new BadRequestException("오류 던지기");

        return new ApiResponse<>("데이터가 들어갈 자리(문자열, 객체 등");
    }

}
