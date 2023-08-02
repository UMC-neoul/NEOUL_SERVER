package com.example.neoul.dto.product;

import com.example.neoul.dto.brand.BrandRes;
import lombok.*;

import java.time.LocalDate;
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
    public static class makeLikedProductRes {
        private Long productId; //상품 id
        private boolean liked;
    }





    @Builder
    @Setter
    @Getter
    public static class getLikedProductRes {
        private int count; //찜한 상품 개수
        private List<ProductRes.LikedProductList> likedProducts;
    }

    @Builder
    @Setter
    @Getter
    // product/상세
    public static class LikedProductList {
        private Long likedProductId; //상품 id
        //private List<String> productImgList; //상품사진
        private Long brandId; //브랜드 id
        private String brandName; //브랜드 이름
        private String productName; //상품 이름
        private Integer price; //상품 가격
    }









}
