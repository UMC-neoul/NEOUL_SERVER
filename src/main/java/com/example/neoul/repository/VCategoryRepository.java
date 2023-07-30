package com.example.neoul.repository;

import com.example.neoul.entity.category.VCategory.VCategory;
import org.springframework.data.jpa.repository.JpaRepository;

public interface VCategoryRepository extends JpaRepository<VCategory, Long> {
}
