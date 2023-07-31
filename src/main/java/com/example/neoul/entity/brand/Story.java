package com.example.neoul.entity.brand;


import com.example.neoul.entity.category.CategoryV;
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
@Table(name = "story")
@Entity
public class Story extends BaseEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "story_id")
    private Long storyId;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "vcategory_id")
    private CategoryV storyVCategory;

    @Column(name = "img")
    private String img;

    @Column(name = "title")
    private String title;

    @Column(name = "content")
    private String content;



    //TODO dto로 수정하기 나중에
    public static Story recruitBrand() {

        Story story = Story.builder()

                .build();

        return story;
    }

}