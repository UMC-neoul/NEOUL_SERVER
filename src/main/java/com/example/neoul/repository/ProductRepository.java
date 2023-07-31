package com.example.neoul.repository;


import com.example.neoul.entity.brand.Brand;
import com.example.neoul.entity.brand.Product;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;


@Repository
public interface ProductRepository extends JpaRepository<Product, Long> {
    List<Product> findAllByBrand(Brand brand);
}
