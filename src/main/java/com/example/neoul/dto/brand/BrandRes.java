package com.example.neoul.dto.brand;

import com.example.neoul.entity.brand.Brand;
import com.example.neoul.entity.brand.Product;
import com.example.neoul.entity.category.CategoryP;
import com.example.neoul.entity.category.CategoryV;
import lombok.*;

import javax.persistence.*;
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
        private Long id; //브랜드 id
        private Long categoryVId; //카테고리 id
        private String categoryVName; // 후원 카테고리 이름
        private String name; //브랜드 이름
        private String intro; //한 줄소개
        private String profileImg; //브랜드 프로필 이미지
        private List<ProductListRes> products;
//        private Long hid; //해시태그 id
//        private List<String> hashTag; //해시태그 내용

//        private String bCreatedAt; //입점일시
//        private Integer bLikeCNT; //브랜드 찜개수
//        private Boolean bHearted; //브랜드 찜 여부 true false
    }

    @Getter
    @Setter
    public static class ProductListRes {
        private Long id;
        private String name;
        private Integer price;
        private String deliveryInfo; //배송정보
        private String productUrl; //상풍 상세 url
    }


    @Builder
    @NoArgsConstructor
    @AllArgsConstructor
    @Setter
    @Getter
    // brand/상세
    public static class BrandInfoRes {
        private Long id; //브랜드 id
        private Long categoryVId; //카테고리 id
        private String categoryVName; // 후원 카테고리 이름
        private String name; //브랜드 이름
        private String intro; //한 줄소개
        private String profileImg; //브랜드 프로필 이미지

        private List<ProductListRes> products; //TODO -> 이것도 DTO로 반환처리 해야 할 것 같아!

        //        private Long hid; //해시태그 id
        private List<String> hashTag; //해시태그 내용 //TODO -> 이것도 DTO로 반환처리 해야 할 것 같아!

        private String createdAt; //입점일시
        private Integer likeCNT; //브랜드 찜개수
        private Boolean hearted; //브랜드 찜 여부 true false
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
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "brand_id")
    private Long brandId;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "vcategory_id")
    private CategoryV brandVCategory;

    @Column(name = "name")
    private String name;

    @Column(name = "intro")
    private String intro;

    @Column(name = "img")
    private String profileImg;

    @OneToMany(mappedBy = "brand",fetch = FetchType.LAZY)
    private List<Product> products; //TODO -> 이것도 DTO로 반환처리 해야 할 것 같아!
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
        private Long bid; //브랜드 id //TODO -> 반환할 땐 요약하지 말고 최대한 자세히 적어주기!!
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
