package com.example.neoul.repository;

import com.example.neoul.entity.category.CategoryV;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CategoryVRepository extends JpaRepository<CategoryV, Long> {
}
