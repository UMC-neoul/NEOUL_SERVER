package com.example.neoul.controller.StoryController;

import com.example.neoul.dto.Story.StoryRes;
import com.example.neoul.dto.brand.BrandRes;
import com.example.neoul.entity.brand.Story;
import com.example.neoul.global.entity.ApiResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/story")
public class StoryController {
    private final StoryService storyService;

    // 스토리 list
    @GetMapping("/list")
    public ApiResponse<List<StoryRes.StoryListRes>> list(){
        return new ApiResponse(storyService.list());
    }

    // 스토리 상세조회
    @GetMapping("/{storyId}")
    public ApiResponse<StoryRes.StoryInfoRes> storyInfo(@PathVariable("storyId") Long storyId){
        return new ApiResponse<>(storyService.info(storyId));
    }
}

