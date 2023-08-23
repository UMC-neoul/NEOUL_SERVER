package com.example.neoul.dto.board;

import lombok.*;

import java.time.LocalDateTime;
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



    //기본 카테고리 상품 조회 시 반환 DTO
    @Builder
    @NoArgsConstructor
    @AllArgsConstructor
    @Setter
    @Getter
    public static class CategoryProduct { //todo 상품 찜 여부 추가
        private Long productId;
        private Long categoryId;
        private String brandName;
        private String productName;
        private Integer price;
        private Integer discountedRatio; //상품 할인률
        private List<String> productImgList; //상품사진
        private boolean liked;
        //private String deliveryInfo;
        //private String productUrl;
        private LocalDateTime createdAt;
    }





    //좋아요순 정렬만 사용
    @Builder
    @NoArgsConstructor
    @AllArgsConstructor
    @Setter
    @Getter
    public static class CategoryProductOrderByLikes implements Comparable<CategoryProductOrderByLikes>{
        private Long productId;
        private Long categoryId;
        private String brandName;
        private String productName;
        private Integer price;
        private Integer discountedRatio; //상품 할인률
        private List<String> productImgList; //상품사진
        //private String deliveryInfo;
        //private String productUrl;
        private int likes;
        private boolean liked;
        private LocalDateTime createdAt;

        @Override
        public int compareTo(CategoryProductOrderByLikes categoryProductOrderByLikes) {
            if(this.likes > categoryProductOrderByLikes.getLikes()) {
                return -1;
            }
            else if(this.likes < categoryProductOrderByLikes.getLikes()) {
                return 1;
            }
            return 0;
        }
    }






}
