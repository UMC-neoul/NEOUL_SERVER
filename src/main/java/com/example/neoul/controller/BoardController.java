package com.example.neoul.controller;


import com.example.neoul.dto.board.BoardRes;
import com.example.neoul.dto.brand.BrandRes;
import com.example.neoul.dto.product.ProductRes;
import com.example.neoul.global.entity.ApiResponse;
import com.example.neoul.global.exception.BadRequestException;
import com.example.neoul.service.BoardService;
import com.example.neoul.service.BrandService;
import com.example.neoul.service.ProductService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@Api(tags={"04.board"})
@RequestMapping("/board")
public class BoardController {

    private final BoardService boardService;
    private final BrandService brandService;
    private final ProductService productService;



    @ApiOperation(value = "카테고리별로 상품 조회하기", notes = "의류(1), 소품(2), 악세사리(3), 잡화(4), 기타(5) | 추천순, 인기순, 신상품순, 낮은 가격순, 높은 가격순")
    @GetMapping("/product/{categoryId}")
    public ApiResponse<List<BoardRes.CategoryBoardSimple>> getCategoryList(@PathVariable Long categoryId,
                                                                           @RequestParam(required = false) Integer option){
        return new ApiResponse<>(boardService.getCategoryList(categoryId, 1));
    }


    //브랜드 리스트, 상세조회
    @ApiOperation(value = "브랜드 조회(+소속 상품)", notes = "브랜드 별 상품 조회")
    @GetMapping("/brand/list")
    public ApiResponse<List<BrandRes.BrandListRes>> getBrandList(){
        return new ApiResponse(brandService.getBrandList());
    }

    @ApiOperation(value = "브랜드 상세 조회", notes = "브랜드 상세 정보")
    @GetMapping("/brand/{brandId}")
    public ApiResponse<BrandRes.BrandInfoRes> getBrandInfo(@PathVariable("brandId") Long brandId){
        return new ApiResponse(brandService.getBrandInfo(brandId));
    }


    //상품 리스트, 상세조회
    @ApiOperation(value = "상품 일반 전체 조회", notes = "상품 전체조회 api 입니다")
    @GetMapping("/product/list")
    public ApiResponse<List<ProductRes.ProductDetailRes>> getAllProducts() {
        return new ApiResponse<>(productService.getAllProducts());
    }

    @ApiOperation(value = "상품 상세 조회", notes = "상품 상세조회 api 입니다. {productId}에 {1} 처럼 상품 id를 넣고 요청을 보내면 상품을 상세조회할 수 있습니다.")
    @GetMapping("/product/{productId}")
    public ApiResponse<ProductRes.ProductDetailRes> getProductById(@PathVariable Long productId) {
        ProductRes.ProductDetailRes product = productService.getProduct(productId);
        return new ApiResponse<>(product);
    }





}
