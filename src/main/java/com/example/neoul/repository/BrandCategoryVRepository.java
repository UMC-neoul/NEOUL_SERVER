package com.example.neoul.repository;

import com.example.neoul.entity.brand.Brand;
import com.example.neoul.entity.brand.BrandCategoryV;
import com.example.neoul.entity.category.CategoryV;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface BrandCategoryVRepository extends JpaRepository<BrandCategoryV, Long> {

    List<BrandCategoryV> findAllByBrand(Brand brand);
    List<BrandCategoryV> findAllByCategoryV(CategoryV categoryV);

}
