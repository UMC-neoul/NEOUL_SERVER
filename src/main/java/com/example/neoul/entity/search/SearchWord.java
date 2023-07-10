package com.example.neoul.entity.search;


import com.example.neoul.global.entity.BaseEntity;
import lombok.*;
import org.hibernate.annotations.DynamicInsert;
import org.hibernate.annotations.DynamicUpdate;
import org.springframework.data.annotation.CreatedDate;

import javax.persistence.*;
import java.time.LocalDateTime;


@DynamicInsert
@DynamicUpdate
@Setter
@Getter
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "searchword")
@Entity
public class SearchWord extends BaseEntity {


    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private Long writerid; //작성자 id

    private String word;

    @CreatedDate
    @Column(name = "created_at", nullable = false, updatable = false) //updatable = false : 수정불가
    private LocalDateTime createdDate; //검색어 최초검색 일시 (검색어 생성)

    //검색어 검색 일시도 있어야 됨







    //TODO dto로 수정하기 나중에
    public static SearchWord recruitBrand() {

        SearchWord searchWord = SearchWord.builder()

                .build();

        return searchWord;
    }

}
