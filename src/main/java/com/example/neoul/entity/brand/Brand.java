package com.example.neoul.entity.brand;


import com.example.neoul.entity.category.VCategory.VCategory;
import com.example.neoul.global.entity.BaseEntity;
import lombok.*;
import org.hibernate.annotations.DynamicInsert;
import org.hibernate.annotations.DynamicUpdate;

import javax.persistence.*;


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

    @Column(name = "category_id", columnDefinition = "TINYINT(0)") //기본 0
    private Integer categoryId; //카테고리 id - 후에 int로 반환되게

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "brand_id")
    private Long brandId;

    @Column(name = "name")
    private String name;

    @Column(name = "intro")
    private String intro;

    @Column(name = "img")
    private String profileImg;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "vcategory_id")
    private VCategory brandVCategory;

//    private Integer bLikeCNT; //브랜드 찜개수
//    private Boolean bHearted; //브랜드 찜 여부 true false




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