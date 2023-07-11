package com.example.neoul.dto;

import lombok.*;
import java.util.List;

public class SearchRes {


    @Builder
    @NoArgsConstructor
    @AllArgsConstructor
    @Setter
    @Getter
    //최근 검색어
    public static class SearchWordRes {
        private Integer id; //검색어 id
        private String word; //검색어
        private String createdAt; //검색어 생성 일시
        private String searchedAt; //검색어 검색 일시 (생성 이후)
        private List<String> searchwords; //검색어 리스트
    }


}
