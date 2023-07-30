package com.example.neoul.entity.category;


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
@Table(name = "category_v")
@Entity
public class CategoryV {


    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "vcatogery_id")
    private Integer vcategory_id;

    @Column(name = "name")
    private String categoryName;







    //TODO dto로 수정하기 나중에
    public static CategoryV recruitBrand() {

        CategoryV brandStory = CategoryV.builder()

                .build();

        return brandStory;
    }

}
