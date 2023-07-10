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
    private Long id;

    private String bname;

    @Column(name = "b_profile_img")
    private String bprofileimg;

    @Column(columnDefinition = "TINYINT(0)") //기본 0
    private Integer cid; //카테고리 id - 후에 int로 반환되게

    private String bintro;

    private Integer sid; //후원story id

    private Integer promid; //프로모션 id

    private Integer hid; //해시태그 id

    private Integer hcnt; //브랜드 찜개수

    @CreatedDate
    @Column(name = "created_at", nullable = false, updatable = false) //updatable = false : 수정불가
    private LocalDateTime createdDate; //브랜드 입점 일시

    @LastModifiedDate
    @Column(name = "last_modified_at", nullable = false)
    private LocalDateTime lastModifiedDate;






    //TODO dto로 수정하기 나중에
    public static Brand recruitBrand() {

        Brand brand = Brand.builder()

                .build();

        return brand;
    }

}
