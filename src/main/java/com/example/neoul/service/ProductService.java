package com.example.neoul.service;

import com.example.neoul.dto.product.ProductRes;
import com.example.neoul.entity.brand.Product;
import com.example.neoul.global.exception.NotFoundException;
import com.example.neoul.repository.ProductRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@RequiredArgsConstructor
@Service
public class ProductService {

    private final ProductRepository productRepository;


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

        return ProductRes.ProductDetailRes.builder()
                .pid(product.getId())
                .bid(product.getBrand().getId())
                .cid(product.getCategoryP().getId())
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
