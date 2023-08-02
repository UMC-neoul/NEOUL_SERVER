package com.example.neoul.controller;

import com.example.neoul.dto.brand.BrandRes;
import com.example.neoul.global.entity.ApiResponse;
import com.example.neoul.service.BrandService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@Api(tags={"02.brand"})
@RequestMapping("/brand")
public class BrandController {
    private final BrandService brandService;

    // 브랜드 list
    @ApiOperation(value = "브랜드별로 상품 조회하기", notes = "브랜드 별 상품 조회")
    @GetMapping("/list")
    public ApiResponse<List<BrandRes.BrandListRes>> getBrandList(){
        return new ApiResponse(brandService.getBrandlist());
    }

    // 브랜드 상세조회
    @ApiOperation(value = "브랜드 상세 내용", notes = "브랜드 상세 정보")
    @GetMapping("/info/{brandId}")
    public ApiResponse<BrandRes.BrandInfoRes> getBrandInfo(@PathVariable("brandId") Long brandId){
        return new ApiResponse(brandService.getBrandInfo(brandId));
    }

    // 브랜드 찜하기
    @ApiOperation(value = "브랜드 찜하기", notes = "브랜드 찜하기")
    @PatchMapping("/like/{brandId}")
    public ApiResponse<BrandRes.makeLikedBrandRes> makeLikedBrand(@PathVariable("brandId") Long brandId){
        return new ApiResponse(brandService.makeLikedBrand(brandId));
    }

    // 브랜드 찜 취소하기
    @ApiOperation(value = "브랜드 찜 취소하기", notes = "브랜드 찜 취소하기")
    @PatchMapping("/dislike/{brandId}")
    public ApiResponse<BrandRes.deleteLikedBrandRes> deleteLikedBrand(@PathVariable("brandId") Long brandId){
        return new ApiResponse(brandService.deleteLikedBrand(brandId));
    }

    // 찜한 브랜드 조회
    @ApiOperation(value = "찜한 브랜드 조회", notes = "찜한 브랜드 조회")
    @GetMapping("/like/list")
    public ApiResponse<BrandRes.getLikedBrandRes> getLikedBrand(){
        return new ApiResponse(brandService.getLikedBrand());
    }
}
