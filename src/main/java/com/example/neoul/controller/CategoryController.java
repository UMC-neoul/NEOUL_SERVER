package com.example.neoul.controller;

import com.example.neoul.entity.category.CategoryP;
import com.example.neoul.service.CategoryService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RequiredArgsConstructor
@RestController
@Api(tags={"00.category"})
@RequestMapping("/category")
public class CategoryController {

    private final CategoryService categoryService;

    /*@ApiOperation(value = "카테고리 반환", notes = "상품 카테고리 입니다")
    @GetMapping("/now")
    public BaseResponse<CategoryP> pCategory() {
        if(categoryService.getPCategory() != null){

        }
    }*/



}
