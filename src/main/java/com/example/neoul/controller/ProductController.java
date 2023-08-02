package com.example.neoul.controller;

import com.example.neoul.dto.product.ProductRes;
import com.example.neoul.entity.brand.Product;
import com.example.neoul.repository.ProductRepository;
import com.example.neoul.service.ProductService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.io.UnsupportedEncodingException;
import java.util.List;



@RestController
@Api(tags={"product"})
@RequestMapping("/product")
//@RequiredArgsConstructor
public class ProductController { //🛍️

    private final ProductService productService;

    @Autowired
    public ProductController(ProductService productService) {
        this.productService = productService;
    }

    @ApiOperation(value = "상품 전체조회", notes = "상품 전체조회 api 입니다")
    @GetMapping("/products")
    public List<ProductRes.RecruitProductRes> getAllProducts() {
        return productService.getAllProducts();
    }

    @ApiOperation(value = "상품 상세조회", notes = "상품 상세조회 api 입니다. {productId}에 {1} 처럼 상품 id를 넣고 요청을 보내면 상품을 상세조회할 수 있습니다.")
    @GetMapping("/product/{productId}")
    public ResponseEntity<ProductRes.RecruitProductRes> getProductById(@PathVariable Long productId) {
        ProductRes.RecruitProductRes product = productService.getProductById(productId);
        if (product != null) {
            return ResponseEntity.ok(product);
        } else {
            return ResponseEntity.notFound().build();
        }
    }



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
