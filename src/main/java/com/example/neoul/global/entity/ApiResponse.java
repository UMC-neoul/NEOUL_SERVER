package com.example.neoul.global.entity;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;
import lombok.Data;

@Data
@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonPropertyOrder({"code", "message", "data"})
public class ApiResponse<T> {

    private int code;
    private String message;
    private T data;


    //오류일 경우
    public ApiResponse(int code, String message) {
        this.code = code;
        this.message = message;
    }


    //성공일 경우
    public ApiResponse(T data) {
        this.code = 200;
        this.message = "요청에 성공하였습니다";
        this.data = data;
    }
}
