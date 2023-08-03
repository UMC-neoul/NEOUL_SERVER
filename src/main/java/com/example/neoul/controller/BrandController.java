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

//    @ApiOperation(value = "브랜드별로 상품 조회하기", notes = "브랜드 별 상품 조회")
//    @GetMapping("/list")
//    public ApiResponse<List<BrandRes.BrandListRes>> getBrandList(){
//        return new ApiResponse(brandService.getBrandList());
//    }
//
//
//    @ApiOperation(value = "브랜드 상세 내용", notes = "브랜드 상세 정보")
//    @GetMapping("/{brandId}")
//    public ApiResponse<BrandRes.BrandInfoRes> getBrandInfo(@PathVariable("brandId") Long brandId){
//        return new ApiResponse(brandService.getBrandInfo(brandId));
//    }


//    @ApiOperation(value = "브랜드 찜하기", notes = "브랜드 찜하기")
//    @PatchMapping("/like/{brandId}")
//    public ApiResponse<String> likeBrand(@PathVariable("brandId") Long brandId){
//        brandService.likeBrand(brandId);
//        return new ApiResponse("브랜드를 찜했습니다");
//    }
//
//    // 브랜드 찜 취소하기
//    @ApiOperation(value = "브랜드 찜 취소하기", notes = "브랜드 찜 취소하기")
//    @PatchMapping("/dislike/{brandId}")
//    public ApiResponse<String> deleteLikedBrand(@PathVariable("brandId") Long brandId){
//        brandService.deleteLikedBrand(brandId);
//        return new ApiResponse("브랜드 찜이 취소가 되었습니다");
//    }
//



}
