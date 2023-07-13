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
    @Column(name = "product_id")
    private Long productId;

    @Column(name = "brand_id") //기본 0
    private Long brandId; //브랜드 id

    @Column(name = "category_id")
    private Long categoryId;

    private String name;

    private Integer price;

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
