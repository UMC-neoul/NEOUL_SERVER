package com.example.neoul.dto.board;

import io.swagger.annotations.ApiModelProperty;
import lombok.*;

public class BoardReq {


    @Builder
    @NoArgsConstructor
    @AllArgsConstructor
    @Setter
    @Getter
    public static class RecruitQAReq{
        @ApiModelProperty(example = "14") //문의글 id
        private Long qid;
        @ApiModelProperty(example = "test@gmail.com")
        private String username;
        @ApiModelProperty(example = "kakao")
        private String social;
    }

}
