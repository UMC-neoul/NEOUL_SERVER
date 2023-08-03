package com.example.neoul.entity.brand;


import com.example.neoul.entity.category.CategoryV;
import com.example.neoul.entity.user.User;
import com.example.neoul.global.entity.BaseEntity;
import lombok.*;
import org.hibernate.annotations.DynamicInsert;
import org.hibernate.annotations.DynamicUpdate;

import javax.persistence.*;
import java.util.List;


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

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "vcategory_id")
    private CategoryV categoryV;

    @Column(name = "name")
    private String name;

    @Column(name = "intro")
    private String intro;

    @Column(name = "img")
    private String profileImg;

    // 추가 - son
//    @ManyToOne(fetch = FetchType.LAZY)
//    @JoinColumn(name = "liked_user_id")
//    private User likedUser;
//
//    @OneToMany(mappedBy = "brand",fetch = FetchType.LAZY)
//    private List<Product> products; //TODO -> 이것도 DTO로 반환처리 해야 할 것 같아!



    //TODO dto로 수정하기 나중에
    public static Brand recruitBrand() {

        Brand brand = Brand.builder()

                .build();

        return brand;
    }

}