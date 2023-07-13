package com.example.neoul.repository;

import com.example.neoul.entity.brand.BrandStory;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface StoryRepository extends JpaRepository<BrandStory, Long> {

}
