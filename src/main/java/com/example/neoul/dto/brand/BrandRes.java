package com.example.neoul.dto.brand;

import com.example.neoul.entity.brand.Product;
import lombok.*;
import java.util.List;

public class BrandRes {

    /*
    여기서 부터------------------------------------------
     */

    @Builder
    @NoArgsConstructor
    @AllArgsConstructor
    @Setter
    @Getter
    // brand (전체)
    public static class BrandListRes {
        private Long bid; //브랜드 id
        private Long categoryVId; //카테고리 id
        private String categoryVName; // 후원 카테고리 이름
        private String bName; //브랜드 이름
        private String bIntro; //한 줄소개
        private String bProfileImg; //브랜드 프로필 이미지

        private List<Product> products;
//        private Long hid; //해시태그 id
//        private List<String> hashTag; //해시태그 내용

//        private String bCreatedAt; //입점일시
//        private Integer bLikeCNT; //브랜드 찜개수
//        private Boolean bHearted; //브랜드 찜 여부 true false
    }

    @Builder
    @NoArgsConstructor
    @AllArgsConstructor
    @Setter
    @Getter
    // brand/상세
    public static class BrandInfoRes {
        private Long bid; //브랜드 id
        private Long categoryVId; //카테고리 id
        private String categoryVName; // 후원 카테고리 이름
        private String bName; //브랜드 이름
        private String bIntro; //한 줄소개
        private String bProfileImg; //브랜드 프로필 이미지

        private List<Product> products;

        //        private Long hid; //해시태그 id
        private List<String> hashTag; //해시태그 내용

        private String bCreatedAt; //입점일시
        private Integer bLikeCNT; //브랜드 찜개수
        private Boolean bHearted; //브랜드 찜 여부 true false
//
//        private Long sid; //후원 id
//        private String preImg; //후원글 프리뷰 이미지
//        private String title; //후원글 제목
//        private String content; //후원글 내용
//        private String sCreatedAt; //후원글 작성 일시

//        private Long pid; //프로모션 id
//        private String promImg; // 프로모션 배너 이미지
//        private String pTitle; // 프로모션 제목
//        private String pContent; // 프로모션 내용
//        private String pCreatedAt; // 프로모션 생성 일시


    }

    /*
    여기까지 Son ------------------------------------------
     */

    @Builder
    @NoArgsConstructor
    @AllArgsConstructor
    @Setter
    @Getter
    // (확정x) 프로모션 - 브랜드 프로모션
    public static class RecruitPromRes {
        private Long bid; //브랜드 id
        private Long pid; //프로모션 id
        private String promImg; // 프로모션 배너 이미지
        private String pTitle; // 프로모션 제목
        private String pContent; // 프로모션 내용
        private String createdAt; // 프로모션 생성 일시
    }


    @Builder
    @NoArgsConstructor
    @AllArgsConstructor
    @Setter
    @Getter
    private static class LikedBrandRes {
        private Boolean hearted; //true or false
        private String result; //좋아요 성공 or 좋아요 취소 성공
    }



}
