package com.example.neoul.service;

import com.example.neoul.dto.brand.BrandRes;
import com.example.neoul.entity.brand.Brand;
import com.example.neoul.entity.brand.BrandCategoryV;
import com.example.neoul.entity.brand.Product;
import com.example.neoul.entity.brand.ProductImage;
import com.example.neoul.entity.user.User;
import com.example.neoul.entity.user.UserLikedBrand;
import com.example.neoul.global.exception.BadRequestException;
import com.example.neoul.global.exception.NotFoundException;
import com.example.neoul.repository.*;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.beans.BeanUtils;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
@Slf4j
public class BrandService {
    private final BrandRepository brandRepository;
    private final ProductRepository productRepository;

    private final ProductImageRepository productImageRepository;
    private final UserService userService;

    private final UserLikedBrandRepository userLikedBrandRepository;

    private final BrandCategoryVRepository brandCategoryVRepository;


    public Brand getBrandByBrandId(Long brandId){
        Optional<Brand> optionalBrand = brandRepository.findById(brandId);
        if(optionalBrand.isEmpty()) {
            throw new NotFoundException("존재하지 않는 브랜드입니다");
        }
        return optionalBrand.get();
    }


    public List<BrandRes.BrandListRes> getBrandList() {
        List<Brand> brandList = brandRepository.findAll();
        return makeBrandList(brandList);
    }

    public List<BrandRes.BrandListRes> makeBrandList(List<Brand> brandList) {
        List<BrandRes.BrandListRes> responseList = new ArrayList<>();
        for (Brand brand : brandList) {
            List<BrandCategoryV> brandCategoryVList = brandCategoryVRepository.findAllByBrand(brand);

            BrandRes.BrandListRes brandListRes = BrandRes.BrandListRes.builder()
                    .brandId(brand.getId())
                    .categoryVId(brandCategoryVList.stream()
                            .map(item -> item.getCategoryV().getId())
                            .collect(Collectors.toList()))
                    .categoryVName(brandCategoryVList.stream()
                            .map(item -> item.getCategoryV().getName())
                            .collect(Collectors.toList()))
                    .name(brand.getName())
                    .intro(brand.getIntro())
                    .profileImg(brand.getProfileImg())
                    .productList(productList(brand))
                    .build();
            responseList.add(brandListRes);
        }
        return responseList;
    }

    public List<BrandRes.ProductListRes> productList(Brand brand) {
        List<BrandRes.ProductListRes> result = new ArrayList<>();
        List<Product> products = productRepository.findAllByBrand(brand); //한 브랜드에 대한 상품 리스트

        for(Product product : products){
            List<ProductImage> images = productImageRepository.findAllByProduct(product);
            List<String> productImgList = new ArrayList<>();

            for(ProductImage productImage : images){
                productImgList.add(productImage.getUrl());
            }

            BrandRes.ProductListRes e = BrandRes.ProductListRes.builder()
                    .productId(product.getId())
                    .name(product.getName())
                    .price(product.getDiscountedSalePrice())
                    .deliveryInfo(product.getDeliveryInfo())
                    .productUrl(product.getProductUrl())
                    .productImgList(productImgList)
                    .build();

            result.add(e);
        }

        return result; //한 브랜드에 대한 상품 리스트
    }



    public BrandRes.BrandInfoRes getBrandInfo(Long brandId) {
        Brand brand = brandRepository.findById(brandId).get(); // 실패시 exception 발생

        BrandRes.BrandInfoRes brandInfo = BrandRes.BrandInfoRes.builder()
                .brandId(brand.getId())
                .categoryVId(brandCategoryVRepository.findAllByBrand(brand).stream()
                        .map(item -> item.getCategoryV().getId())
                        .collect(Collectors.toList()))
                .categoryVName(brandCategoryVRepository.findAllByBrand(brand).stream()
                        .map(item -> item.getCategoryV().getName())
                        .collect(Collectors.toList()))
                .brandName(brand.getName())
                .intro(brand.getIntro())
                .profileImg(brand.getProfileImg())
                .productList(productList(brand))
//                .hashTag() // 해시태그 내용
//                .bCreatedAt()
//                .bLikeCNT() // 찜개수
//                .bHearted() // 찜 여부
                .build();
        return brandInfo;
    }

    public void likeBrand(Long brandId) {
        User user = userService.findNowLoginUser();
        Brand brand = getBrandByBrandId(brandId);
        UserLikedBrand userLikedBrand = UserLikedBrand.builder()
                .user(user)
                .brand(brand)
                .build();

        userLikedBrandRepository.save(userLikedBrand);
    }

    public void deleteLikedBrand(Long brandId) {
        User user = userService.findNowLoginUser();
        Brand brand = getBrandByBrandId(brandId);

        UserLikedBrand userLikedBrand = userLikedBrandRepository.findByUserAndBrand(user, brand).orElse(null);

        if(userLikedBrand == null)
            throw new BadRequestException("해당 브랜드를 찜하지 않았습니다");

        userLikedBrandRepository.delete(userLikedBrand);
    }

    public BrandRes.getLikedBrandRes getUserLikedBrand() {
        User user = userService.findNowLoginUser();
        List<UserLikedBrand> userLikedBrandList = userLikedBrandRepository.findAllByUser(user);
        List<BrandRes.LikedBrandList> list = new ArrayList<>();

        for(UserLikedBrand userLikedBrand : userLikedBrandList){
            BrandRes.LikedBrandList e = BrandRes.LikedBrandList.builder()
                    .brandId(userLikedBrand.getBrand().getId())
                    .brandName(userLikedBrand.getBrand().getName())
                    .brandIntro(userLikedBrand.getBrand().getIntro())
                    .brandImg(userLikedBrand.getBrand().getProfileImg())
                    .build();
            list.add(e);
        }


        return BrandRes.getLikedBrandRes.builder()
                .userId(user.getUserId())
                .brandCnt(list.size())
                .likedBrands(list)
                .build();
    }


//    public BrandRes.makeLikedBrandRes makeLikedBrand(Long brandId){
//        User user = userService.findNowLoginUser();
//        Brand likedBrand = brandRepository.findById(brandId)
//                .orElseThrow(() -> new NotFoundException("브랜드가 존재하지 않습니다"));
//        likedBrand.setLikedUser(user);
//        brandRepository.save(likedBrand);
//        log.info("브랜드 찜 완료");
//
//        BrandRes.makeLikedBrandRes likedBrandRes= BrandRes.makeLikedBrandRes.builder()
//                .likedBrandId(brandId)
//                .likedBrandName(likedBrand.getName())
//                .build();
//
//        return likedBrandRes;
//    }
//
//    public BrandRes.deleteLikedBrandRes deleteLikedBrand(Long brandId){
//        Brand dislikedBrand = brandRepository.findById(brandId)
//                .orElseThrow(() -> new NotFoundException("브랜드가 존재하지 않습니다"));
//        dislikedBrand.setLikedUser(null);
//        brandRepository.save(dislikedBrand);
//        log.info("브랜드 찜 취소 완료");
//
//        BrandRes.deleteLikedBrandRes dislikedBrandRes= BrandRes.deleteLikedBrandRes.builder()
//                .dislikedBrandId(brandId)
//                .dislikedBrandName(dislikedBrand.getName())
//                .build();
//
//        return dislikedBrandRes;
//    }
//
//
//    public BrandRes.getLikedBrandRes getLikedBrand(){
//        User user = userService.findNowLoginUser();
//        List<BrandRes.LikedBrandList> likedBrandList= brandRepository.findAllByLikedUser(user).stream()
//                .map( origin -> {
//                    BrandRes.LikedBrandList likedBrand = BrandRes.LikedBrandList.builder()
//                            .likedBrandId(origin.getId())
//                            .categoryVId(origin.getCategoryV().getId())
//                            .categoryVName(origin.getCategoryV().getName())
//                            .name(origin.getName())
//                            .intro(origin.getIntro())
//                            .profileImg(origin.getProfileImg())
//                            .build();
//                    return likedBrand;
//                })
//                .collect(Collectors.toList());
//
//        return BrandRes.getLikedBrandRes.builder()
//                .count(likedBrandList.size())
//                .likedBrands(likedBrandList)
//                .build();
//    }
}
