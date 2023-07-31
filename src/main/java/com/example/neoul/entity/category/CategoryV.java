package com.example.neoul.entity.category;


import com.example.neoul.entity.brand.Brand;
import com.example.neoul.entity.brand.Story;
import lombok.*;
import org.hibernate.annotations.DynamicInsert;
import org.hibernate.annotations.DynamicUpdate;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;


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
    @Column(name = "vcategory_id")
    private Long vcategoryId;

    @Column(name = "name")
    private String name;




    //이건 그냥 리포지토리에서 ALL로 불러와도 될 듯!
    // story 들에 대한 관계
    @OneToMany(mappedBy = "storyVCategory")
    private List<Story> stories = new ArrayList<Story>();

    // brand 들에 대한 관계
    @OneToMany(mappedBy = "brandVCategory")
    private List<Brand> brands = new ArrayList<Brand>();





    //TODO dto로 수정하기 나중에
    public static CategoryV recruitBrand() {

        CategoryV brandStory = CategoryV.builder()

                .build();

        return brandStory;
    }

}
