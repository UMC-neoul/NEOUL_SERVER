package com.example.neoul.entity.user;

import com.example.neoul.entity.brand.Brand;
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
@Table(name = "user_liked_brand")
@Entity
public class UserLikedBrand {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    @JoinColumn(name = "user_id")
    @ManyToOne(fetch = FetchType.LAZY)
    private User user;

    @JoinColumn(name = "brand_id")
    @ManyToOne(fetch = FetchType.LAZY)
    private Brand brand;
}
