package com.example.neoul.repository;

import com.example.neoul.entity.user.UserLikedProduct;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserLikedProductRepository extends JpaRepository<UserLikedProduct, Long> {
}
