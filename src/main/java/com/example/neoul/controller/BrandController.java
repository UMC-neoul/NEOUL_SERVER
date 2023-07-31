package com.example.neoul.controller;

import com.example.neoul.dto.brand.BrandRes;
import com.example.neoul.global.entity.ApiResponse;
import com.example.neoul.service.BrandService;
import io.swagger.annotations.Api;
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
    @GetMapping("/list")
    public ApiResponse<List<BrandRes.BrandListRes>> list(){
        return new ApiResponse(brandService.list());
    }

    // 브랜드 상세조회
    @GetMapping("/{brandId}")
    public ApiResponse<BrandRes.BrandInfoRes> brandInfo(@PathVariable("brandId") Long brandId){
        return new ApiResponse<>(brandService.info(brandId));
    }

}
