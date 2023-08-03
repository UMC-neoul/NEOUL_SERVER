package com.example.neoul.service;

import com.example.neoul.dto.product.ProductRes;
import com.example.neoul.entity.brand.Product;
import com.example.neoul.entity.brand.ProductImage;
import com.example.neoul.global.exception.NotFoundException;
import com.example.neoul.repository.ProductImageRepository;
import com.example.neoul.repository.ProductRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@RequiredArgsConstructor
@Service
public class ProductService {

    private final ProductRepository productRepository;

    private final ProductImageRepository productImageRepository;


    // 상품 전체 리스트
    public List<ProductRes.ProductDetailRes> getAllProducts() {
        List<Product> products = productRepository.findAll();
        List<ProductRes.ProductDetailRes> result = new ArrayList<>();

        for(Product product : products){
            ProductRes.ProductDetailRes e = getProduct(product.getId());
            result.add(e);
        }

        return result;
    }

    public ProductRes.ProductDetailRes getProduct(Long productId) {
        Product product = getProductByProductId(productId);
        List<ProductImage> productImages = productImageRepository.findAllByProduct(product);
        List<String> productImgList = new ArrayList<>();

        for(ProductImage productImage : productImages){
            productImgList.add(productImage.getUrl());
        }

        return ProductRes.ProductDetailRes.builder()
                .productId(product.getId())
                .brandId(product.getBrand().getId())
                .categoryPId(product.getCategoryP().getId())
                .categoryName(product.getCategoryP().getName()) //얘는 살려야함
                .productName(product.getName())
                .price(product.getPrice())
                .productImgList(productImgList) //얘는 살려야함
                .deliveryInfo(product.getDeliveryInfo())
                .productUrl(product.getProductUrl())
//                .pLikeCNT(product.getLikeCount())
//                .pHearted(product.isHearted())
                .createdAt(product.getCreatedAt().toLocalDate())
//                .clickedAt(product.getClickedAt())
                .build();
    }


    public Product getProductByProductId(Long productId){
        Optional<Product> optionalProduct = productRepository.findById(productId);
        if(optionalProduct.isEmpty()) {
            throw new NotFoundException("존재하지 않는 상품입니다");
        }
        return optionalProduct.get();
    }



    /*public Product getProduct(final Long pid) {
        return productRepository.findById(pid).orElseThrow(() -> new IllegalArgumentException("no such data"));
    }*/





}
