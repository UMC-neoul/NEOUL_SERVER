package com.example.neoul.entity.brand;


import com.example.neoul.entity.category.CategoryP;
import com.example.neoul.entity.category.CategoryV;
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

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "brand_id") //기본 0
    private Brand brand; //브랜드 id

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "pcategory_id")
    private CategoryP categoryP;

    private String name;

    private Integer price;

    private Integer discountedRatio;

    private Integer discountedSalePrice;

    @Column(name = "delivery_info")
    private String deliveryInfo; //배송정보

    @Column(name = "product_url")
    private String productUrl; //상풍 상세 url






    //TODO dto로 수정하기 나중에
    public static Product recruitBrand() {

        Product product = Product.builder()

                .build();

        return product;
    }

}
