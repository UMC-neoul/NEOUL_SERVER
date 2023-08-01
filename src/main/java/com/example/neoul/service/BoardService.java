package com.example.neoul.service;

import com.example.neoul.dto.board.BoardRes;
import com.example.neoul.entity.brand.Product;
import com.example.neoul.entity.category.CategoryP;
import com.example.neoul.global.exception.NotFoundException;
import com.example.neoul.repository.CategoryPRepository;
import com.example.neoul.repository.ProductRepository;
import lombok.RequiredArgsConstructor;
import org.hibernate.cfg.CreateKeySecondPass;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class BoardService {

    private final ProductRepository productRepository;
    private final CategoryPRepository categoryPRepository;

    public CategoryP getCategoryPByCategoryId(Long categoryId){
        Optional<CategoryP> optionalCategoryP = categoryPRepository.findById(categoryId);
        if(optionalCategoryP.isEmpty()) {
            throw new NotFoundException("존재하지 않는 브랜드 카테고리입니다");
        }
        return optionalCategoryP.get();
    }


    public List<BoardRes.CategoryBoardSimple> getCategoryList(Long categoryId, int option) {
        CategoryP categoryP = getCategoryPByCategoryId(categoryId);
        List<Product> productList = productRepository.findAllByCategoryP(categoryP);
        List<BoardRes.CategoryBoardSimple> result = new ArrayList<>();

        for(Product product : productList){
            BoardRes.CategoryBoardSimple e = BoardRes.CategoryBoardSimple.builder()
                    .productId(product.getProductId())
                    .categoryId(categoryId)
                    .brandName(product.getBrand().getName())
                    .productName(product.getName())
                    .price(product.getPrice())
                    .productUrl(product.getProductUrl())
                    .build();

            result.add(e);
        }


        return result;
    }
}
