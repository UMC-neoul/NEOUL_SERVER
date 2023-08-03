package com.example.neoul.repository;

import com.example.neoul.entity.category.CategoryP;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface CategoryPRepository extends JpaRepository<CategoryP, Long> {

    Optional<CategoryP> findById(Long pcategoryId);

}
