package com.example.neoul.service;

import com.example.neoul.dto.product.ProductReq;
import com.example.neoul.dto.product.ProductRes;
import com.example.neoul.entity.brand.Product;
import com.example.neoul.repository.ProductRepository;
import io.swagger.annotations.Authorization;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import javax.transaction.Transactional;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

//@RequiredArgsConstructor
@Service
public class ProductService {

    private final ProductRepository productRepository;

    @Autowired
    public ProductService(ProductRepository productRepository) {
        this.productRepository = productRepository;
    }

    // 상품 전체 리스트
    public List<ProductRes.RecruitProductRes> getAllProducts() {
        List<Product> products = productRepository.findAll();
        return products.stream().map(this::getProduct).collect(Collectors.toList());
    }

    private ProductRes.RecruitProductRes getProduct(Product product) {
        return ProductRes.RecruitProductRes.builder()
                .pid(product.getProductId())
                .bid(product.getBrandId())
                .cid(product.getPcategoryId())
//                .category(product.getCategory()) //얘는 살려야함
                .pName(product.getName())
                .price(product.getPrice())
//                .pImgList(product.getImgList()) //얘는 살려야함
//                .pDeliveryInfo(product.getDeliveryInfo())
                .pUrl(product.getProductUrl())
//                .pLikeCNT(product.getLikeCount())
//                .pHearted(product.isHearted()) //얘는 살려야함
//                .pCreatedAt(product.getCreatedAt())
//                .clickedAt(product.getClickedAt())
                .build();
    }

    //상품 상세조회
    public ProductRes.RecruitProductRes getProductById(Long productId) {
        Product product = getProduct(productId);
        return getProduct(product);
    }

    public Product getProduct(Long productId) {
        return productRepository.findById(productId).orElseThrow(() -> new IllegalArgumentException("no such data"));
    }

    /*public Product getProduct(final Long pid) {
        return productRepository.findById(pid).orElseThrow(() -> new IllegalArgumentException("no such data"));
    }*/





}
