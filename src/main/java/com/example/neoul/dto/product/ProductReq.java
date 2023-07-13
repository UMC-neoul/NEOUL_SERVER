package com.example.neoul.dto.product;

import io.swagger.annotations.ApiModelProperty;
import lombok.*;
import lombok.extern.java.Log;

public class ProductReq {

    @Builder
    @NoArgsConstructor
    @AllArgsConstructor
    @Setter
    @Getter
    public static class SortProductReq { //상품 정렬 - 동일 카테고리, 찜순, 등록일시, 가격
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
    public static class SearchProductReq { //상품 검색
        @ApiModelProperty(example = "검색어 어쩌구저쩌구") //검색어
        private String word;
    }


    @Builder
    @NoArgsConstructor
    @AllArgsConstructor
    @Setter
    @Getter
    public static class ClickProductReq { //상품 클릭
        @ApiModelProperty(example = "2023-07-10") //클릭 일시
        private String clickedDate;
        @ApiModelProperty(example = "12") //클릭한 상품 id
        private Long pid;
    }


    @Builder
    @NoArgsConstructor
    @AllArgsConstructor
    @Setter
    @Getter
    public static class LikeProductReq { //상품 클릭
        @ApiModelProperty(example = "2023-07-10") //클릭 일시
        private String clickedDate;
        @ApiModelProperty(example = "12") //클릭한 상품 id
        private Long pid;
    }


}
