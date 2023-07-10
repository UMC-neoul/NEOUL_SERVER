package com.example.neoul.entity.brand;


import com.example.neoul.global.entity.BaseEntity;
import lombok.*;
import org.hibernate.annotations.DynamicInsert;
import org.hibernate.annotations.DynamicUpdate;
import org.springframework.data.annotation.CreatedDate;

import javax.persistence.*;
import java.time.LocalDateTime;


@DynamicInsert
@DynamicUpdate
@Setter
@Getter
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "brandpromotion")
@Entity
public class BrandPromotion extends BaseEntity {


    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id; //브랜드 id

    @Column(name = "prom_img")
    private String promimg;

    private String ptitle;

    private String pcontent;

    @CreatedDate
    @Column(name = "created_at", nullable = false, updatable = false) //updatable = false : 수정불가
    private LocalDateTime createdDate; //후원글 생성 날짜








    //TODO dto로 수정하기 나중에
    public static BrandPromotion recruitBrand() {

        BrandPromotion brandPromotion = BrandPromotion.builder()

                .build();

        return brandPromotion;
    }

}
