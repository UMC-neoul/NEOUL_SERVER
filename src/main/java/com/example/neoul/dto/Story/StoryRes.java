package com.example.neoul.dto.Story;

import com.example.neoul.dto.brand.BrandRes;
import lombok.*;

import java.time.LocalDateTime;
import java.util.List;

public class StoryRes {

    @Builder
    @NoArgsConstructor
    @AllArgsConstructor
    @Setter
    @Getter
    // content와 content/상세 - 브랜드 후원 이야기
    public static class StoryListRes {
        private Long sid; //후원 이야기 id
        private String categoryVName; // 후원 카테고리 이름
        private String preImg; //후원글 프리뷰 이미지
        private String title; //후원글 제목
        private LocalDateTime createdAt; //후원글 작성 일시
    }

    @Builder
    @NoArgsConstructor
    @AllArgsConstructor
    @Setter
    @Getter
    // content와 content/상세 - 브랜드 후원 이야기
    public static class StoryInfoRes {
        private Long sid; //후원 이야기 id
//        private Long bid; //브랜드 id
        private String categoryVName; // 후원 브랜드 이름
        private String preImg; //후원글 프리뷰 이미지
        private String title; //후원글 제목
        private String content; //후원글 내용
        private LocalDateTime createdAt; //후원글 작성 일시
        private List<BrandRes.BrandListRes> brandListRes; // 브랜드 별 물품 리스트
    }
}
