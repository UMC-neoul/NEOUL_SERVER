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


    /*@GetMapping("/product/{pid}")
    public ResponseEntity<Product> getProduct(@PathVariable Long pid) {

        return ResponseEntity.ok()
                .body(productService.getProduct(pid).get());
    }*/


    /*// 브랜드 list
    @GetMapping("/list")
    public ApiResponse<List<BrandRes.BrandListRes>> list(){
        return new ApiResponse(brandService.list());
    }

    // 브랜드 상세조회
    @GetMapping("/{brandId}")
    public ApiResponse<BrandRes.BrandInfoRes> brandInfo(@PathVariable("brandId") Long brandId){
        return new ApiResponse<>(brandService.info(brandId));
    }*/







    /*//ReadAll
    @GetMapping("/product")
    public List<Product> readAllProduct(){
        return productRepository.findAll();
    }

    @GetMapping("/product/{pid}")
    public ApiResponse<List<ProductRes.RecruitProductRes>> getProduct(@PathVariable Long productId,
                                                                      @RequestParam(required = false) Integer option){
        return new ApiResponse<>(productService.getProduct(productId, 1));
    }*/




}
