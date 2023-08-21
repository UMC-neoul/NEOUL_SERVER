package com.example.neoul.service;

import com.example.neoul.dto.board.BoardRes;
import com.example.neoul.entity.brand.Product;
import com.example.neoul.entity.brand.ProductImage;
import com.example.neoul.entity.category.CategoryP;
import com.example.neoul.global.exception.NotFoundException;
import com.example.neoul.repository.CategoryPRepository;
import com.example.neoul.repository.ProductImageRepository;
import com.example.neoul.repository.ProductRepository;
import com.example.neoul.repository.UserLikedProductRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class BoardService {

    private final ProductRepository productRepository;
    private final CategoryPRepository categoryPRepository;
    private final ProductImageRepository productImageRepository;

    private final UserLikedProductRepository userLikedProductRepository;

    public CategoryP getCategoryPByCategoryId(Long categoryId){
        Optional<CategoryP> optionalCategoryP = categoryPRepository.findById(categoryId);
        if(optionalCategoryP.isEmpty()) {
            throw new NotFoundException("존재하지 않는 브랜드 카테고리입니다");
        }
        return optionalCategoryP.get();
    }



    //TODO 추천순은 추후 수정 예정
    public List<BoardRes.CategoryProduct> getCategoryProductListOrderByRecommendation(Long categoryId) {
        CategoryP categoryP = getCategoryPByCategoryId(categoryId);
        List<Product> productList = productRepository.findAllByCategoryP(categoryP);
        List<BoardRes.CategoryProduct> result = new ArrayList<>();

        for(Product product : productList){
            List<ProductImage> images = productImageRepository.findAllByProduct(product);
            List<String> productImgList = new ArrayList<>();

            for(ProductImage productImage : images){
                productImgList.add(productImage.getUrl());
            }

            BoardRes.CategoryProduct e = BoardRes.CategoryProduct.builder()
                    .productId(product.getId())
                    .categoryId(categoryId)
                    .brandName(product.getBrand().getName())
                    .productName(product.getName())
                    .price(product.getPrice())
                    .discountedRatio(product.getDiscountedRatio())
                    .productImgList(productImgList)
                    .createdAt(product.getCreatedAt())
                    .build();

            result.add(e);
        }


        return result;
    }



    public List<BoardRes.CategoryProductOrderByLikes> getCategoryProductListOrderByLikes(Long categoryId) {
        CategoryP categoryP = getCategoryPByCategoryId(categoryId);
        List<Product> productList = productRepository.findAllByCategoryP(categoryP);
        List<BoardRes.CategoryProductOrderByLikes> result = new ArrayList<>();

        for(Product product : productList){
            int likes = userLikedProductRepository.countAllByProduct(product);

            List<ProductImage> images = productImageRepository.findAllByProduct(product);
            List<String> productImgList = new ArrayList<>();

            for(ProductImage productImage : images){
                productImgList.add(productImage.getUrl());
            }

            BoardRes.CategoryProductOrderByLikes e = BoardRes.CategoryProductOrderByLikes.builder()
                    .productId(product.getId())
                    .categoryId(categoryId)
                    .brandName(product.getBrand().getName())
                    .productName(product.getName())
                    .price(product.getPrice())
                    .discountedRatio(product.getDiscountedRatio())
                    .productImgList(productImgList)
                    .likes(likes)
                    .createdAt(product.getCreatedAt())
                    .build();

            result.add(e);
        }

        Collections.sort(result);

        return result;
    }

    public List<BoardRes.CategoryProduct> getCategoryProductListByCreatedAtDesc(Long categoryId) {
        CategoryP categoryP = getCategoryPByCategoryId(categoryId);
        List<Product> productList = productRepository.findAllByCategoryPOrderByCreatedAtDesc(categoryP);
        List<BoardRes.CategoryProduct> result = new ArrayList<>();

        for(Product product : productList){
            List<ProductImage> images = productImageRepository.findAllByProduct(product);
            List<String> productImgList = new ArrayList<>();

            for(ProductImage productImage : images){
                productImgList.add(productImage.getUrl());
            }

            BoardRes.CategoryProduct e = BoardRes.CategoryProduct.builder()
                    .productId(product.getId())
                    .categoryId(categoryId)
                    .brandName(product.getBrand().getName())
                    .productName(product.getName())
                    .price(product.getPrice())
                    .discountedRatio(product.getDiscountedRatio())
                    .productImgList(productImgList)
                    .createdAt(product.getCreatedAt())
                    .build();

            result.add(e);
        }


        return result;
    }

    public List<BoardRes.CategoryProduct> getCategoryProductListByPriceAsc(Long categoryId) {
        CategoryP categoryP = getCategoryPByCategoryId(categoryId);
        List<Product> productList = productRepository.findAllByCategoryPOrderByPriceAsc(categoryP);
        List<BoardRes.CategoryProduct> result = new ArrayList<>();

        for(Product product : productList){
            List<ProductImage> images = productImageRepository.findAllByProduct(product);
            List<String> productImgList = new ArrayList<>();

            for(ProductImage productImage : images){
                productImgList.add(productImage.getUrl());
            }

            BoardRes.CategoryProduct e = BoardRes.CategoryProduct.builder()
                    .productId(product.getId())
                    .categoryId(categoryId)
                    .brandName(product.getBrand().getName())
                    .productName(product.getName())
                    .price(product.getPrice())
                    .discountedRatio(product.getDiscountedRatio())
                    .productImgList(productImgList)
                    .createdAt(product.getCreatedAt())
                    .build();

            result.add(e);
        }


        return result;
    }

    public List<BoardRes.CategoryProduct> getCategoryProductListByPriceDesc(Long categoryId) {
        CategoryP categoryP = getCategoryPByCategoryId(categoryId);
        List<Product> productList = productRepository.findAllByCategoryPOrderByPriceDesc(categoryP);
        List<BoardRes.CategoryProduct> result = new ArrayList<>();

        for(Product product : productList){
            List<ProductImage> images = productImageRepository.findAllByProduct(product);
            List<String> productImgList = new ArrayList<>();

            for(ProductImage productImage : images){
                productImgList.add(productImage.getUrl());
            }

            BoardRes.CategoryProduct e = BoardRes.CategoryProduct.builder()
                    .productId(product.getId())
                    .categoryId(categoryId)
                    .brandName(product.getBrand().getName())
                    .productName(product.getName())
                    .price(product.getPrice())
                    .discountedRatio(product.getDiscountedRatio())
                    .productImgList(productImgList)
                    .createdAt(product.getCreatedAt())
                    .build();

            result.add(e);
        }


        return result;
    }
}
