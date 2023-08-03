package com.example.neoul.repository;

import com.example.neoul.entity.brand.Brand;
import com.example.neoul.entity.brand.Product;
import com.example.neoul.entity.user.User;
import com.example.neoul.entity.user.UserLikedBrand;
import com.example.neoul.entity.user.UserLikedProduct;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface UserLikedProductRepository extends JpaRepository<UserLikedProduct, Long> {

    List<UserLikedProduct> findAllByProduct(Product product);
    List<UserLikedProduct> findAllByUser(User user);

    Optional<UserLikedProduct> findByUserAndProduct(User user, Product product);

    int countAllByProduct(Product product);
}
