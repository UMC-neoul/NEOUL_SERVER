package com.example.neoul.repository;

import com.example.neoul.entity.brand.Brand;
import com.example.neoul.entity.user.User;
import com.example.neoul.entity.user.UserLikedBrand;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface UserLikedBrandRepository extends JpaRepository<UserLikedBrand, Long> {
    List<UserLikedBrand> findAllByBrand(Brand brand);
    List<UserLikedBrand> findAllByUser(User user);

    Optional<UserLikedBrand> findByUserAndBrand(User user, Brand brand);
}
