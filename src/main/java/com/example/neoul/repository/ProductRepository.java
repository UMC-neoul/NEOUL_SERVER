package com.example.neoul.repository;


import com.example.neoul.entity.brand.Brand;
import com.example.neoul.entity.brand.Product;
import com.example.neoul.entity.category.CategoryP;
import com.example.neoul.entity.category.CategoryV;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;


@Repository
public interface ProductRepository extends JpaRepository<Product, Long> {
    List<Product> findAllByBrand(Brand brand);

    List<Product> findAllByCategoryP(CategoryP categoryP);

    List<Product> findAllByCategoryPOrderByCreatedAtDesc(CategoryP categoryP);

    List<Product> findAllByCategoryPOrderByPriceAsc(CategoryP categoryP);
    List<Product> findAllByCategoryPOrderByPriceDesc(CategoryP categoryP);

    Optional<Product> findById(Long productId);

}
