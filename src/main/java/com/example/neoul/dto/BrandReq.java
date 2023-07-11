package com.example.neoul.dto;

import io.swagger.annotations.ApiModelProperty;
import lombok.*;

public class BrandReq {

    @Builder
    @NoArgsConstructor
    @AllArgsConstructor
    @Setter
    @Getter
    public static class SortBrandReq { //안써
        @ApiModelProperty(example = "카테고리 id 0,1,2, ..") //추천순
        private Long cid;
        @ApiModelProperty(example = "10") //찜 순
        private Integer hCNT;
        @ApiModelProperty(example = "2023-07-10")
        private String createdAt; //등록일시
        @ApiModelProperty(example = "12300")
        private Integer price; //가격순
    }


    @Builder
    @NoArgsConstructor
    @AllArgsConstructor
    @Setter
    @Getter
    public static class SearchBrandReq { //브랜드 정렬 - 동일 카테고리, 찜순, 등록일시, 가격
        @ApiModelProperty(example = "검색어 어쩌구저쩌구") //추천순
        private String word;
    }


    @Builder
    @NoArgsConstructor
    @AllArgsConstructor
    @Setter
    @Getter
    public static class LikeBrandReq {
        @ApiModelProperty(example = "2023-07-10") //클릭 일시
        private String clickedDate;
        @ApiModelProperty(example = "12") //클릭한 브랜드 id
        private Long bid;
    }


}
