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
@Table(name = "category_p")
@Entity
public class CategoryP {


    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "pcatogery_id")
    private Long pcategory_id;

    @Column(name = "name")
    private String name;







    //TODO dto로 수정하기 나중에
    public static CategoryP recruitBrand() {

        CategoryP brandStory = CategoryP.builder()

                .build();

        return brandStory;
    }

}
