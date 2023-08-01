package com.example.neoul.service;

import com.example.neoul.dto.Story.StoryRes;
import com.example.neoul.entity.brand.Brand;
import com.example.neoul.entity.brand.Story;
import com.example.neoul.entity.category.CategoryV;
import com.example.neoul.global.exception.NotFoundException;
import com.example.neoul.repository.BrandRepository;
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
    private final BrandRepository brandRepository;
    private final BrandService brandService;

    public List<StoryRes.StoryListRes> getStoryList() {
        List<CategoryV> vCategoryList = categoryVRepository.findAll();
        List<StoryRes.StoryListRes> responseList = new ArrayList<>();
        for (CategoryV vCategory : vCategoryList) {
            Story story = storyRepository.findAllByCategoryV(vCategory).stream().findAny()
                    .orElseThrow(() -> new NotFoundException("스토리가 존재하지 않습니다."));
            StoryRes.StoryListRes StoryListRes = StoryRes.StoryListRes.builder()
                    .id(story.getId())
                    .categoryVName(story.getCategoryV().getName())
                    .preImg(story.getImg())
                    .title(story.getTitle())
                    .createdAt(story.getCreatedAt())
                    .build();
            responseList.add(StoryListRes);
        }
        return responseList;
    }

    public StoryRes.StoryInfoRes getStoryInfo(Long storyId) {
        Story story = storyRepository.findById(storyId).
                orElseThrow(() -> new NotFoundException("스토리가 존재하지 않습니다."));
        List<Brand> brandList = brandRepository.findAllByCategoryV(story.getCategoryV());
        // 응답 리스트
        StoryRes.StoryInfoRes responseList = StoryRes.StoryInfoRes.builder()
                .id(storyId)
                .categoryVName(story.getCategoryV().getName())
                .preImg(story.getImg())
                .title(story.getTitle())
                .content(story.getContent())
                .createdAt(story.getCreatedAt())
                .brandListRes(brandService.makeBrandList(brandList))
                .build();

        return responseList;
    }
}

