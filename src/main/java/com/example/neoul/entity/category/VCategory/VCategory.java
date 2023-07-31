//package com.example.neoul.entity.category.VCategory;
//
//import com.example.neoul.entity.brand.Brand;
//import com.example.neoul.entity.brand.Story;
//import lombok.*;
//import org.hibernate.annotations.DynamicInsert;
//import org.hibernate.annotations.DynamicUpdate;
//
//import javax.persistence.*;
//import java.util.ArrayList;
//import java.util.List;
//
//@DynamicInsert
//@DynamicUpdate
//@Setter
//@Getter
//@Builder
//@NoArgsConstructor
//@AllArgsConstructor
//@Table(name = "category_v")
//@Entity
//// 카테고리 - 후원
//// 유기견, 미혼모 등등
//public class VCategory {
//    @Id
//    @GeneratedValue(strategy = GenerationType.IDENTITY)
//    @Column(name = "vcategory_id")
//    private Long category_id;
//
//    @Column(name = "name")
//    private String categoryName;
//
//    // story 들에 대한 관계
//    @OneToMany(mappedBy = "storyVCategory")
//    private List<Story> stories = new ArrayList<Story>();
//
//    // brand 들에 대한 관계
//    @OneToMany(mappedBy = "brandVCategory")
//    private List<Brand> brands = new ArrayList<Brand>();
//}
