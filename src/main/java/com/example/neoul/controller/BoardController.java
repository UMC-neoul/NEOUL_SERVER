package com.example.neoul.controller;


import com.example.neoul.dto.board.BoardRes;
import com.example.neoul.global.entity.ApiResponse;
import com.example.neoul.global.exception.BadRequestException;
import com.example.neoul.service.BoardService;
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

    @ApiOperation(value = "카테고리별로 상품 조회하기", notes = "의류(1), 소품(2), 악세사리(3), 잡화(4), 기타(5) | 추천순, 인기순, 신상품순, 낮은 가격순, 높은 가격순")
    @GetMapping("/{categoryId}")
    public ApiResponse<List<BoardRes.CategoryBoardSimple>> getCategoryList(@PathVariable Long categoryId,
                                                                           @RequestParam(required = false) Integer option){
        return new ApiResponse<>(boardService.getCategoryList(categoryId, 1));
    }



}
