package com.example.neoul.controller.StoryController;

import com.example.neoul.controller.brandController.BrandService;
import com.example.neoul.dto.Story.StoryRes;
import com.example.neoul.entity.brand.Brand;
import com.example.neoul.entity.brand.Story;
import com.example.neoul.entity.category.CategoryV;
import com.example.neoul.global.exception.NotFoundException;
import com.example.neoul.repository.StoryRepository;
import com.example.neoul.repository.CategoryVRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
public class StoryService {

    private final StoryRepository storyRepository;
    private final CategoryVRepository categoryVRepository;
    private final BrandService brandService;

    public List<StoryRes.StoryListRes> list() {
        List<CategoryV> vCategoryList = categoryVRepository.findAll();
        List<StoryRes.StoryListRes> responseList = new ArrayList<>();
        for (CategoryV vCategory : vCategoryList) {
            Story story = vCategory.getStories().stream().findAny()
                    .orElseThrow(() -> new NotFoundException("스토리가 존재하지 않습니다."));
            StoryRes.StoryListRes StoryListRes = StoryRes.StoryListRes.builder()
                    .sid(story.getStoryId())
                    .categoryVName(story.getStoryVCategory().getName())
                    .preImg(story.getImg())
                    .title(story.getTitle())
                    .createdAt(story.getCreatedAt())
                    .build();
            responseList.add(StoryListRes);
        }
        return responseList;
    }

    public StoryRes.StoryInfoRes info(Long storyId) {
        Story story = storyRepository.findById(storyId).
                orElseThrow(() -> new NotFoundException("스토리가 존재하지 않습니다."));
        List<Brand> brandList = story.getStoryVCategory().getBrands();
        // 응답 리스트
        StoryRes.StoryInfoRes responseList = StoryRes.StoryInfoRes.builder()
                .sid(storyId)
                .categoryVName(story.getStoryVCategory().getName())
                .preImg(story.getImg())
                .title(story.getTitle())
                .content(story.getContent())
                .createdAt(story.getCreatedAt())
                .brandListRes(brandService.makeBrandList(brandList))
                .build();

        return responseList;
    }
}

