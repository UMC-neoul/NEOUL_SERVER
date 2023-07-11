package com.example.neoul.dto;

import io.swagger.annotations.ApiModelProperty;
import lombok.*;

public class SearchReq {


    @Builder
    @NoArgsConstructor
    @AllArgsConstructor
    @Setter
    @Getter
    public static class SearchWordReq { //검색
        @ApiModelProperty(example = "검색어 어쩌구저쩌구") //검색어
        private String word;
    }
}
