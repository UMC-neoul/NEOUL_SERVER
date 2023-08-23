package com.example.neoul.service;

import com.example.neoul.dto.Story.StoryRes;
import com.example.neoul.entity.brand.Brand;
import com.example.neoul.entity.brand.BrandCategoryV;
import com.example.neoul.entity.brand.Story;
import com.example.neoul.entity.category.CategoryV;
import com.example.neoul.global.exception.NotFoundException;
import com.example.neoul.repository.BrandCategoryVRepository;
import com.example.neoul.repository.BrandRepository;
import com.example.neoul.repository.StoryRepository;
import com.example.neoul.repository.CategoryVRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class StoryService {

    private final StoryRepository storyRepository;
    private final CategoryVRepository categoryVRepository;
    private final BrandRepository brandRepository;
    private final BrandService brandService;
    private final BrandCategoryVRepository brandCategoryVRepository;

    public List<StoryRes.StoryListRes> getStoryList() {
        List<StoryRes.StoryListRes> responseList = new ArrayList<>();
        List<Story> storyList = storyRepository.findAll();
        for (Story story : storyList) {
            StoryRes.StoryListRes StoryListRes = StoryRes.StoryListRes.builder()
                    .storyId(story.getId())
                    .categoryVName(story.getCategoryV().getName())
                    .preImg(story.getImg())
                    .title(story.getTitle())
                    .createdAt(story.getCreatedAt())
                    .build();
            responseList.add(StoryListRes);
        }
        return responseList;

//        List<CategoryV> vCategoryList = categoryVRepository.findAll();
//        List<StoryRes.StoryListRes> responseList = new ArrayList<>();
//        for (CategoryV vCategory : vCategoryList) {
//            Story story = storyRepository.findAllByCategoryV(vCategory).stream().findAny()
//                    .orElseThrow(() -> new NotFoundException("스토리가 존재하지 않습니다."));
//            StoryRes.StoryListRes StoryListRes = StoryRes.StoryListRes.builder()
//                    .storyId(story.getId())
//                    .categoryVName(story.getCategoryV().getName())
//                    .preImg(story.getImg())
//                    .title(story.getTitle())
//                    .createdAt(story.getCreatedAt())
//                    .build();
//            responseList.add(StoryListRes);
//        }
//        return responseList;
    }

    public StoryRes.StoryInfoRes getStoryInfo(Long storyId) {
        Story story = storyRepository.findById(storyId).
                orElseThrow(() -> new NotFoundException("스토리가 존재하지 않습니다."));
        List<Brand> brandList = brandCategoryVRepository.findAllByCategoryV(story.getCategoryV()).stream()
                .map(item -> item.getBrand())
                .collect(Collectors.toList());
        // 응답 리스트
        StoryRes.StoryInfoRes responseList = StoryRes.StoryInfoRes.builder()
                .storyId(storyId)
                .categoryVName(story.getCategoryV().getName())
                .preImg(story.getImg())
                .title(story.getTitle())
                .content(story.getContent())
                .createdAt(story.getCreatedAt())
                .brandList(brandService.makeBrandList(brandList))
                .build();

        return responseList;
    }
}

