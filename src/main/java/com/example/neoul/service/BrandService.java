package com.example.neoul.service;

import com.example.neoul.dto.brand.BrandRes;
import com.example.neoul.entity.brand.Brand;
import com.example.neoul.entity.brand.Product;
import com.example.neoul.repository.BrandRepository;
import com.example.neoul.repository.ProductRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.beans.BeanUtils;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class BrandService {
    private final BrandRepository brandRepository;
    private final ProductRepository productRepository;

    public List<BrandRes.BrandListRes> getBrandlist(){
        List<Brand> brandList= brandRepository.findAll();
        return makeBrandList(brandList);
    }

    public List<BrandRes.BrandListRes> makeBrandList(List<Brand> brandList){
        List<BrandRes.BrandListRes> responseList = new ArrayList<>();
        for(Brand brand : brandList){
            BrandRes.BrandListRes brandListRes = BrandRes.BrandListRes.builder()
                    .id(brand.getId())
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

    public BrandRes.BrandInfoRes getBrandInfo(Long brandId){
        Brand brand = brandRepository.findById(brandId).get(); // 실패시 exception 발생
        BrandRes.BrandInfoRes brandInfo = BrandRes.BrandInfoRes.builder()
                .id(brand.getId())
                .categoryVId(brand.getCategoryV().getId())
                .categoryVName(brand.getCategoryV().getName())
                .name(brand.getName())
                .intro(brand.getIntro())
                .profileImg(brand.getProfileImg())
                .products(productList(brand))
//                .hashTag() // 해시태그 내용
//                .bCreatedAt()
//                .bLikeCNT() // 찜개수
//                .bHearted() // 찜 여부
                .build();
        return brandInfo;

    }

    public List<BrandRes.ProductListRes> productList(Brand brand){
        return productRepository.findAllByBrand(brand).stream()
                .map(source -> {
                    BrandRes.ProductListRes target = new BrandRes.ProductListRes(); // 새로운 Product 객체 생성 (속성 복사를 위한 대상 객체)
                    BeanUtils.copyProperties(source, target); // 속성 복사
                    return target;
                })
                .collect(Collectors.toList());
    }
}