package com.example.neoul.entity.brand;


import com.example.neoul.global.entity.BaseEntity;
import lombok.*;
import org.hibernate.annotations.DynamicInsert;
import org.hibernate.annotations.DynamicUpdate;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;

import javax.persistence.*;
import java.time.LocalDateTime;


@DynamicInsert
@DynamicUpdate
@Setter
@Getter
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "brand")
@Entity
public class Brand extends BaseEntity {



    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "brand_id")
    private Long brandId;

    @Column(name = "vcategory_id")
    private Long vcategoryId;

    @Column(name = "name")
    private String name;

    @Column(name = "intro")
    private String intro;

    @Column(name = "img")
    private String profileImg;

    /*@CreatedDate
    @Column(name = "created_at", nullable = false, updatable = false) //updatable = false : 수정불가
    private LocalDateTime createdDate; //브랜드 입점 일시

    @LastModifiedDate
    @Column(name = "last_modified_at", nullable = false)
    private LocalDateTime lastModifiedDate;*/


    //TODO dto로 수정하기 나중에
    public static Brand recruitBrand() {

        Brand brand = Brand.builder()

                .build();

        return brand;
    }

}