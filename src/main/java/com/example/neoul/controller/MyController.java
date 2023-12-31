package com.example.neoul.controller;

import com.example.neoul.dto.board.BoardReq;
import com.example.neoul.dto.brand.BrandRes;
import com.example.neoul.dto.product.ProductRes;
import com.example.neoul.global.entity.ApiResponse;
import com.example.neoul.service.BrandService;
import com.example.neoul.service.ProductService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@Api(tags={"02.my"})
@RequestMapping("/my")
public class MyController {

    private final BrandService brandService;
    private final ProductService productService;


    //브랜드 찜하기
    @ApiOperation(value = "브랜드 찜하기", notes = "브랜드 찜하기")
    @PatchMapping("/brand/like/{brandId}")
    public ApiResponse<String> likeBrand(@PathVariable("brandId") Long brandId){
        brandService.likeBrand(brandId);
        return new ApiResponse("브랜드를 찜했습니다");
    }


    @ApiOperation(value = "브랜드 찜 취소하기", notes = "브랜드 찜 취소하기")
    @PatchMapping("/brand/dislike/{brandId}")
    public ApiResponse<String> deleteLikedBrand(@PathVariable("brandId") Long brandId){
        brandService.deleteLikedBrand(brandId);
        return new ApiResponse("브랜드 찜이 취소가 되었습니다");
    }

    @ApiOperation(value = "내가 찜한 브랜드 조회", notes = "찜한 브랜드 조회")
    @GetMapping("/like/brand/list")
    public ApiResponse<BrandRes.getLikedBrandRes> getUserLikedBrand(){
        return new ApiResponse(brandService.getUserLikedBrand());
    }




    @ApiOperation(value = "상품 찜하기", notes = "상품 찜하기")
    @PatchMapping("/product/like/{productId}")
    public ApiResponse<String> likeProduct(@PathVariable("productId") Long productId){
        productService.likeProduct(productId);
        return new ApiResponse("상품을 찜했습니다");
    }


    @ApiOperation(value = "상품 찜 취소하기", notes = "상품 찜 취소하기")
    @PatchMapping("/product/dislike/{productId}")
    public ApiResponse<String> deleteLikedProduct(@PathVariable("productId") Long productId){
        productService.deleteLikedProduct(productId);
        return new ApiResponse("상품 찜이 취소가 되었습니다");
    }


    @ApiOperation(value = "내가 찜한 상품 조회", notes = "찜한 상품 조회")
    @GetMapping("/like/product/list")
    public ApiResponse<ProductRes.getLikedProductRes> getUserLikedProduct(){
        return new ApiResponse(productService.getUserLikedProduct());
    }


    @ApiOperation(value = "내가 최근 조회한 상품 리스트", notes = "내가 최근 조회한 상품 리스트")
    @GetMapping("/click/product/list")
    public ApiResponse<List<ProductRes.ProductSimpleRes>> getUserRecentlyClickedProduct(){
        return new ApiResponse(productService.getUserRecentlyClickedProduct());
    }


    @ApiOperation(value = "내가 최근 조회한 상품 디비에 저장", notes = "내가 최근 조회한 상품 디비에 저장")
    @PostMapping("/click/product")
    public ApiResponse<String> createUserRecentlyClickedProduct(@RequestBody BoardReq.RecentlyClickedReq recentlyClickedReq){
        productService.createUserRecentlyClickedProduct(recentlyClickedReq);
        return new ApiResponse("최근 조회 상품이 등록되었습니다");
    }


}
