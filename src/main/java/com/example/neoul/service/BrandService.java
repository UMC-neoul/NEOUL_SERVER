package com.example.neoul.service;

import com.example.neoul.dto.brand.BrandRes;
import com.example.neoul.entity.brand.Brand;
import com.example.neoul.entity.user.User;
import com.example.neoul.global.exception.NotFoundException;
import com.example.neoul.repository.BrandRepository;
import com.example.neoul.repository.ProductRepository;
import com.example.neoul.repository.UserLikedBrandRepository;
import com.example.neoul.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;
import org.springframework.beans.BeanUtils;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
@Slf4j
public class BrandService {
    private final BrandRepository brandRepository;
    private final ProductRepository productRepository;
    private final UserService userService;


    public List<BrandRes.BrandListRes> getBrandlist(){
        List<Brand> brandList= brandRepository.findAll();
        return makeBrandList(brandList);
    }

    public List<BrandRes.BrandListRes> makeBrandList(List<Brand> brandList){
        List<BrandRes.BrandListRes> responseList = new ArrayList<>();
        for(Brand brand : brandList){
            BrandRes.BrandListRes brandListRes = BrandRes.BrandListRes.builder()
                    .brandId(brand.getId())
                    .categoryVId(brand.getCategoryV().getId())
                    .categoryVName(brand.getCategoryV().getName())
                    .name(brand.getName())
                    .intro(brand.getIntro())
                    .profileImg(brand.getProfileImg())
                    .products(productList(brand))
                    .build();
            responseList.add(brandListRes);
        }
        return responseList;
    }

    public List<BrandRes.ProductListRes> productList(Brand brand){
        return productRepository.findAllByBrand(brand).stream()
                .map(source -> {
                    BrandRes.ProductListRes target = new BrandRes.ProductListRes(); // 새로운 Product 객체 생성 (속성 복사를 위한 대상 객체)
                    BeanUtils.copyProperties(source, target); // 속성 복사
                    target.setProductId(source.getId());
                    return target;
                })
                .collect(Collectors.toList());
    }

    public BrandRes.BrandInfoRes getBrandInfo(Long brandId){
        Brand brand = brandRepository.findById(brandId).get(); // 실패시 exception 발생
        BrandRes.BrandInfoRes brandInfo = BrandRes.BrandInfoRes.builder()
                .brandId(brand.getId())
                .categoryVId(brand.getCategoryV().getId())
                .categoryVName(brand.getCategoryV().getName())
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

    public BrandRes.makeLikedBrandRes makeLikedBrand(Long brandId){
        User user = userService.findNowLoginUser();
        Brand likedBrand = brandRepository.findById(brandId)
                .orElseThrow(() -> new NotFoundException("브랜드가 존재하지 않습니다"));
        likedBrand.setLikedUser(user);
        brandRepository.save(likedBrand);
        log.info("브랜드 찜 완료");

        BrandRes.makeLikedBrandRes likedBrandRes= BrandRes.makeLikedBrandRes.builder()
                .likedBrandId(brandId)
                .likedBrandName(likedBrand.getName())
                .build();

        return likedBrandRes;
    }

    public BrandRes.deleteLikedBrandRes deleteLikedBrand(Long brandId){
        Brand dislikedBrand = brandRepository.findById(brandId)
                .orElseThrow(() -> new NotFoundException("브랜드가 존재하지 않습니다"));
        dislikedBrand.setLikedUser(null);
        brandRepository.save(dislikedBrand);
        log.info("브랜드 찜 취소 완료");

        BrandRes.deleteLikedBrandRes dislikedBrandRes= BrandRes.deleteLikedBrandRes.builder()
                .dislikedBrandId(brandId)
                .dislikedBrandName(dislikedBrand.getName())
                .build();

        return dislikedBrandRes;
    }


    public BrandRes.getLikedBrandRes getLikedBrand(){
        User user = userService.findNowLoginUser();
        List<BrandRes.LikedBrandList> likedBrandList= brandRepository.findAllByLikedUser(user).stream()
                .map( origin -> {
                    BrandRes.LikedBrandList likedBrand = BrandRes.LikedBrandList.builder()
                            .likedBrandId(origin.getId())
                            .categoryVId(origin.getCategoryV().getId())
                            .categoryVName(origin.getCategoryV().getName())
                            .name(origin.getName())
                            .intro(origin.getIntro())
                            .profileImg(origin.getProfileImg())
                            .build();
                    return likedBrand;
                })
                .collect(Collectors.toList());

        return BrandRes.getLikedBrandRes.builder()
                .count(likedBrandList.size())
                .likedBrands(likedBrandList)
                .build();
    }
}