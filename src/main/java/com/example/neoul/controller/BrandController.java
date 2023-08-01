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
    @GetMapping("/{brandId}")
    public ApiResponse<BrandRes.BrandInfoRes> getBrandInfo(@PathVariable("brandId") Long brandId){
        return new ApiResponse(brandService.getBrandInfo(brandId));
    }

}
