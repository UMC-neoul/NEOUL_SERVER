package com.example.neoul.entity.qa;


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
@Table(name = "qa")
@Entity
public class QA extends BaseEntity {


    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private Long writerid; //작성자 id

    @Column(name = "aid") //답글테이블 id
    private Long aid;

    private String qtitle;

    private String qcontent;


    /*@CreatedDate
    @Column(name = "created_at", nullable = false, updatable = false) //updatable = false : 수정불가
    private LocalDateTime createdDate; //작성 일시*/







    //TODO dto로 수정하기 나중에
    public static QA recruitBrand() {

        QA qa = QA.builder()

                .build();

        return qa;
    }

}
