package com.example.neoul.controller;

import com.example.neoul.dto.brand.BrandRes;
import com.example.neoul.dto.product.ProductRes;
import com.example.neoul.global.entity.ApiResponse;
import com.example.neoul.service.ProductService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;



@RequiredArgsConstructor
@RestController
@Api(tags={"05.product"})
@RequestMapping("/product")
public class ProductController { //🛍️

    private final ProductService productService;


//    @ApiOperation(value = "상품 전체조회", notes = "상품 전체조회 api 입니다")
//    @GetMapping("/list")
//    public ApiResponse<List<ProductRes.ProductDetailRes>> getAllProducts() {
//        return new ApiResponse<>(productService.getAllProducts());
//    }
//
//    @ApiOperation(value = "상품 상세조회", notes = "상품 상세조회 api 입니다. {productId}에 {1} 처럼 상품 id를 넣고 요청을 보내면 상품을 상세조회할 수 있습니다.")
//    @GetMapping("/{productId}")
//    public ApiResponse<ProductRes.ProductDetailRes> getProductById(@PathVariable Long productId) {
//        ProductRes.ProductDetailRes product = productService.getProduct(productId);
//        return new ApiResponse<>(product);
//    }
//
//
//



//    @ApiOperation(value = "상품 찜/찜취소", notes = "상품 찜/찜취소 api 입니다. 해당 상품이 찜 되어 있으면 찜 취소가 되고, 찜이 되어있지 않으면 찜이 됩니다. {productId}에 {1} 처럼 상품 id를 넣고 요청을 보내면 됩니다.")
//    @PatchMapping("/like/{productId}")
//    public ApiResponse<ProductRes.makeLikedProductRes> makeLikedProduct(@PathVariable("productId") Long productId){
//        return new ApiResponse(productService.makeLikedProduct(productId));
//    }
//
//
//    @ApiOperation(value = "찜한 상품 목록 조회", notes = "찜한 상품의 목록을 조회 합니다.")
//    @GetMapping("/like/list")
//    public ApiResponse<ProductRes.getLikedProductRes> getLikedProduct(){
//        return new ApiResponse<>(productService.getLikedProduct());
//    }







}
