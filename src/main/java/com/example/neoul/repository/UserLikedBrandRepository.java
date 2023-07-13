package com.example.neoul.repository;

import com.example.neoul.entity.user.UserLikedBrand;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserLikedBrandRepository extends JpaRepository<UserLikedBrand, Long> {
}
