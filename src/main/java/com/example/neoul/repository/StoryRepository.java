package com.example.neoul.repository;

import com.example.neoul.entity.brand.Product;
import com.example.neoul.entity.brand.Story;
import com.example.neoul.entity.category.CategoryV;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface StoryRepository extends JpaRepository<Story, Long> {
    List<Story> findAllByCategoryV(CategoryV categoryV);
}
