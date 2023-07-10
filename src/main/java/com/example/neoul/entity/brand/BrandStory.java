package com.example.neoul.entity.brand;


import com.example.neoul.global.entity.BaseEntity;
import lombok.*;
import org.hibernate.annotations.DynamicInsert;
import org.hibernate.annotations.DynamicUpdate;
import org.springframework.data.annotation.CreatedDate;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.HashSet;
import java.util.Set;


@DynamicInsert
@DynamicUpdate
@Setter
@Getter
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "brandstory")
@Entity
public class BrandStory extends BaseEntity {


    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private Long bid; //브랜드 id

    @Column(name = "pre_img")
    private String preimg;

    private String title;

    private String content;

    @CreatedDate
    @Column(name = "created_at", nullable = false, updatable = false) //updatable = false : 수정불가
    private LocalDateTime createdDate; //후원글 생성 날짜








    //TODO dto로 수정하기 나중에
    public static BrandStory recruitBrand() {

        BrandStory brandStory = BrandStory.builder()

                .build();

        return brandStory;
    }

}
