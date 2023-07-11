package com.example.neoul.dto;

import lombok.*;

import java.util.Date;
import java.util.List;

public class ProductRes {


    @Builder
    @NoArgsConstructor
    @AllArgsConstructor
    @Setter
    @Getter
    // main - 상품조회
    public static class RecruitProductRes {
        private Long pid; //상품 id
        private Long bid; //브랜드 id
        private Long cid; //카테고리 id
        private String category; //상품 카테고리
        private String pName; //상품명
        private Integer price; //상품금액
        private List<String> pImgList; //상품사진
        private String pDeliveryInfo; //상품 배송정보 (무료배송 etc..)
        private String pUrl; //상품상세 url
        private Integer pLikeCNT; //상품찜 개수
        private Boolean pHearted; //상품찜 여부 true false
        private String pCreatedAt; //상품 업로드 일자

        private String clickedAt; //상품 클릭 일시
    }





}
