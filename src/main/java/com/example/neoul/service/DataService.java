package com.example.neoul.service;

import com.example.neoul.dto.Story.StoryRes;
import com.example.neoul.entity.brand.Brand;
import com.example.neoul.entity.brand.Product;
import com.example.neoul.entity.brand.ProductImage;
import com.example.neoul.entity.brand.Story;
import com.example.neoul.entity.category.CategoryP;
import com.example.neoul.repository.BrandRepository;
import com.example.neoul.repository.CategoryPRepository;
import com.example.neoul.repository.ProductImageRepository;
import com.example.neoul.repository.ProductRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Optional;

@Slf4j
@RequiredArgsConstructor
@Service
public class DataService {
    private final BrandRepository brandRepository;
    private final ProductRepository productRepository;
    private final ProductImageRepository productImageRepository;
    private final CategoryPRepository categoryPRepository;

    public void saveBrands(List<Map<String, Object>> brandDataList) {
        for (Map<String, Object> brandData : brandDataList) {
            String name = (String) brandData.get("bname");
            String intro = (String) brandData.get("bintro");
            String img = (String) brandData.get("bimg");

            Brand brand = Brand.builder()
                    .name(name)
                    .intro(intro).
                    profileImg(img)
                    .build();

            // 저장할 필드가 없는 경우는 무시
            brandRepository.save(brand); // brandRepository는 Brand 엔터티의 JpaRepository
        }
    }

    public void saveProducts(List<Map<String, Object>> productDataList) {
        for (Map<String, Object> productData : productDataList) {

            String name = (String) productData.get("pname");
            String categoryPName = (String) productData.get("pcategory2");

            // categoryP 가 없는 경우에는 만들고, 있는 경우에는 그것을 지정하는 람다식
            CategoryP categoryP = categoryPRepository.findByName(categoryPName)
                    .orElseGet(() -> categoryPRepository.save(CategoryP.builder().name(categoryPName).build()));

            log.info(categoryP.getName());
            System.out.println(categoryP.getName());
            Integer salePrice = (Integer) productData.get("psalePrice");
            String url = (String) productData.get("purl");
            String img = (String) productData.get("pimg");
            Integer discountedRatio = (Integer) productData.get("pdiscountedRatio");
            Integer discountedSalePrice = (Integer) productData.get("pdiscountedSalePrice");
            String deliveryInfo = "배송비 : " + productData.get("pbaseFee").toString();

            // ProductImage 클래스 만들기.
            ProductImage productImage = new ProductImage().builder()
                            .product(productRepository.findByName(name))
                            .url(img).build();

            productImageRepository.save(productImage);

            Product product = Product.builder()
                    .name(name)
                    .brand(brandRepository.findByName((String) productData.get("bname")))
                    .categoryP(categoryP)
                    .price(salePrice)
                    .discountedRatio(discountedRatio)
                    .discountedSalePrice(discountedSalePrice)
                    .productUrl(url)
                    .deliveryInfo(deliveryInfo)
                    .build();

            productRepository.save(product);
        }
    }

}
