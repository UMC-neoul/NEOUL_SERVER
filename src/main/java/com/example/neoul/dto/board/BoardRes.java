package com.example.neoul.dto.board;

import io.swagger.annotations.ApiModelProperty;
import lombok.*;

import java.util.List;

public class BoardRes {


    @Builder
    @NoArgsConstructor
    @AllArgsConstructor
    @Setter
    @Getter
    //(화면x) 문의글
    public static class QABoardRes {
        private Long qid; //문의글 id
        private Long writerid; //작성자 id
        private String username; //작성자 이름
        private String qTitle; //문의글 제목
        private String qContent; //문의글 내용
        private String qCreatedAt; //문의글 생성 일시

        private String aContent; //답글 내용
        //private List<String> aContents; //답글 여러개 하려면
        private String aCreatedAt; //답변 일자
    }






}
