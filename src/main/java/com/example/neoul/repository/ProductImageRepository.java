package com.example.neoul.repository;

import com.example.neoul.entity.brand.Product;
import com.example.neoul.entity.brand.ProductImage;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ProductImageRepository extends JpaRepository<ProductImage, Long> {

    List<ProductImage> findAllByProduct(Product product);
}
