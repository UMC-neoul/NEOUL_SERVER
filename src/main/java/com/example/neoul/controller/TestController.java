package com.example.neoul.controller;

import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@Slf4j
@RestController
@RequestMapping("/test")
public class TestController {
    @GetMapping("/testapi")
    public ResponseEntity test(){
        log.info("test api 수신");
        return new ResponseEntity("test ok",HttpStatus.OK);
    }
}
