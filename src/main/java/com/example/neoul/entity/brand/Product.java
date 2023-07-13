package com.example.neoul.entity.brand;


import com.example.neoul.global.entity.BaseEntity;
import lombok.*;
import org.hibernate.annotations.DynamicInsert;
import org.hibernate.annotations.DynamicUpdate;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.HashSet;
import java.util.List;
import java.util.Set;


@DynamicInsert
@DynamicUpdate
@Setter
@Getter
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "product")
@Entity
public class Product extends BaseEntity {


    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private Long bid; //브랜드 id

    @Column(columnDefinition = "TINYINT(0)") //기본 0
    private Integer cid; //카테고리 id - 후에 int로 반환되게

    private String pname;

    private Long price;

    @ElementCollection(fetch = FetchType.LAZY)
    private Set<String> pimglist = new HashSet<String>();

    private String pdeliveryinfo; //배송정보

    private String purl; //상풍 상세 url

    private Integer plikecnt; //찜개수

    /*@CreatedDate
    @Column(name = "created_at", nullable = false, updatable = false) //updatable = false : 수정불가
    private LocalDateTime createdDate; //상품 업로드 날짜*/








    //TODO dto로 수정하기 나중에
    public static Product recruitBrand() {

        Product product = Product.builder()

                .build();

        return product;
    }

}
