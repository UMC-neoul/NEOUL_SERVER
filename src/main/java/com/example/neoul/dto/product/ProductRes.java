package com.example.neoul.dto.product;

import com.example.neoul.dto.brand.BrandRes;
import lombok.*;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

public class ProductRes {


    @Builder
    @NoArgsConstructor
    @AllArgsConstructor
    @Setter
    @Getter
    // main - 상품 상세조회
    public static class ProductDetailRes {
        private Long productId; //상품 id
        private Long brandId; //브랜드 id
        private Long categoryPId; //카테고리 id
        private String categoryName; //상품 카테고리
        private String productName; //상품명
        private Integer price; //상품금액
        private List<String> productImgList; //상품사진
        private String deliveryInfo; //상품 배송정보 (무료배송 etc..)
        private String productUrl; //상품상세 url
        private Integer pLikeCNT; //상품찜 개수
        private Boolean pHearted; //상품찜 여부 true false
        private LocalDate createdAt; //상품 업로드 일자

 //       private LocalDate clickedAt; //상품 클릭 일시
    }

    @Builder
    @NoArgsConstructor
    @AllArgsConstructor
    @Setter
    @Getter
    public static class ProductSimpleRes {
        private Long productId;
        private String brandName;
        private String productName;
        private Integer price;
        private List<String> productImgList;
        private LocalDateTime clickedAt;
    }


    @Builder
    @NoArgsConstructor
    @AllArgsConstructor
    @Setter
    @Getter
    private static class LikedProductRes {
        private Boolean hearted; //true or false
        private String result; //좋아요 성공 or 좋아요 취소 성공
    }


    @Builder
    @Setter
    @Getter
    public static class getLikedProductRes {
        private Long userId;
        private int productCnt; //브랜드 개수
        private List<ProductRes.LikedProductList> likedProduct;
    }

    @Builder
    @Setter
    @Getter
    public static class LikedProductList {
        private Long productId; //브랜드 id
        private String productName; //브랜드 이름
    }



}
