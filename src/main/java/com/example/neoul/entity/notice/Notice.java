package com.example.neoul.entity.notice;


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
@Table(name = "notice")
@Entity
public class Notice extends BaseEntity {


    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "notice_id")
    private Long noticeId;

    private String title;

    private String content;








    //TODO dto로 수정하기 나중에
    public static Notice recruitBrand() {

        Notice notice = Notice.builder()

                .build();

        return notice;
    }

}
