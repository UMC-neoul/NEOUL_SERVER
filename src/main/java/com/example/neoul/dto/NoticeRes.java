package com.example.neoul.dto;

import lombok.*;
import java.util.List;

public class NoticeRes {


    @Builder
    @NoArgsConstructor
    @AllArgsConstructor
    @Setter
    @Getter
    //(화면x) 공지사항
    public static class RecruitNoticeRes {
        private Long nid; //공지 id
        private String nTitle; //공지 제목
        private String nContent; //공지 내용
        private String nCreatedAt; //공지 생성 일시
    }



}
