package com.example.neoul.service;

import com.example.neoul.dto.brand.BrandRes;
import com.example.neoul.entity.brand.Brand;
import com.example.neoul.global.entity.ApiResponse;
import com.example.neoul.repository.BrandRepository;
import com.example.neoul.repository.ProductRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

@Service
@RequiredArgsConstructor
public class BrandService {
    private final BrandRepository brandRepository;
    private final ProductRepository productRepository;

    public List<BrandRes.BrandListRes> list(){
        List<Brand> brandList= brandRepository.findAll();
        return makeBrandList(brandList);
    }

    public List<BrandRes.BrandListRes> makeBrandList(List<Brand> brandList){
        List<BrandRes.BrandListRes> responseList = new ArrayList<>();
        for(Brand brand : brandList){
            BrandRes.BrandListRes brandListRes = BrandRes.BrandListRes.builder()
                    .bid(brand.getBrandId())
                    .categoryVId(brand.getBrandVCategory().getVcategory_id())
                    .categoryVName(brand.getBrandVCategory().getName())
                    .bName(brand.getName())
                    .bIntro(brand.getIntro())
                    .bProfileImg(brand.getProfileImg())
                    .products(productRepository.findAllByBrandId(brand.getBrandId()))
                    .build();
            responseList.add(brandListRes);
        }
        return responseList;
    }

    public BrandRes.BrandInfoRes info(Long brandId){
        Brand brand = brandRepository.findById(brandId).get(); // 실패시 exception 발생
        BrandRes.BrandInfoRes brandInfo = BrandRes.BrandInfoRes.builder()
                .bid(brand.getBrandId())
                .categoryVId(brand.getBrandVCategory().getVcategory_id())
                .categoryVName(brand.getBrandVCategory().getName())
                .bName(brand.getName())
                .bIntro(brand.getIntro())
                .bProfileImg(brand.getProfileImg())
                .products(productRepository.findAllByBrandId(brand.getBrandId()))
//                .hashTag() // 해시태그 내용
//                .bCreatedAt()
//                .bLikeCNT() // 찜개수
//                .bHearted() // 찜 여부
                .build();

        return brandInfo;

    }
}