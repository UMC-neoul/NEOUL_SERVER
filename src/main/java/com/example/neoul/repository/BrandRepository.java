package com.example.neoul.repository;

import com.example.neoul.dto.brand.BrandRes;
import com.example.neoul.entity.brand.Brand;
import com.example.neoul.entity.brand.Product;
import com.example.neoul.entity.category.CategoryP;
import com.example.neoul.entity.category.CategoryV;
import com.example.neoul.entity.user.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface BrandRepository extends JpaRepository<Brand, Long> {
    List<Brand> findAllByCategoryV(CategoryV categoryV);
    List<Brand> findAllByLikedUser(User user);
}

