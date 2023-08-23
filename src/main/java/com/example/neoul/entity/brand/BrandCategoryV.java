package com.example.neoul.entity.brand;

import com.example.neoul.entity.category.CategoryV;
import com.example.neoul.entity.user.User;
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
@Table(name = "brand_category_v")
@Entity
public class BrandCategoryV {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    @JoinColumn(name = "brand_id")
    @ManyToOne(fetch = FetchType.LAZY)
    private Brand brand;

    @JoinColumn(name = "categoryv_id")
    @ManyToOne(fetch = FetchType.LAZY)
    private CategoryV categoryV;
}
