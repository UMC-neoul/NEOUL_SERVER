package com.example.neoul.controller;

import com.example.neoul.dto.Story.StoryRes;
import com.example.neoul.global.entity.ApiResponse;
import com.example.neoul.service.StoryService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequiredArgsConstructor
@Api(tags={"03.story"})
@RequestMapping("/story")
public class StoryController {
    private final StoryService storyService;

//     스토리 list
    @ApiOperation(value = "각 후원카테고리(유기견, 해양동물..) 별 스토리 리스트 - 카테고리별로 1개씩 나오도록 지정", notes = "카테고리별 스토리 리스트")
    @GetMapping("/list")
    public ApiResponse<List<StoryRes.StoryListRes>> getStoryList(){
        return new ApiResponse(storyService.getStoryList());
    }

    // 스토리 상세조회
    @ApiOperation(value = "스토리 별 상세 조회", notes = "싱글말(1), 독립운동가(2), 해양(3), 반려동물(4), 위안부(5)")
    @GetMapping("/{storyId}")
    public ApiResponse<StoryRes.StoryInfoRes> getStoryInfo(@PathVariable("storyId") Long storyId){
        return new ApiResponse<>(storyService.getStoryInfo(storyId));
    }


}

