package com.example.neoul.service;

import com.example.neoul.entity.brand.Product;
import com.example.neoul.entity.category.CategoryP;
import com.example.neoul.entity.category.CategoryV;
import com.example.neoul.repository.CategoryPRepository;
import com.example.neoul.repository.CategoryVRepository;
import com.example.neoul.repository.ProductRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@RequiredArgsConstructor
@Service
public class CategoryService {
    @Autowired
    private CategoryPRepository categoryPRepository;
    private CategoryVRepository categoryVRepository;

    public List<CategoryP> getpCategoryList() {
        List<CategoryP> categoryP = categoryPRepository.findAll();

        if(!categoryP.isEmpty()) return categoryPRepository.findAll();
        else throw new IllegalArgumentException("no such data");
    }
    public List<CategoryV> getvCategoryList() {
        List<CategoryV> categoryV = categoryVRepository.findAll();

        if(!categoryV.isEmpty()) return categoryVRepository.findAll();
        else throw new IllegalArgumentException("no such data");
    }

    public CategoryP getPCategory(final Long cid) {
        return categoryPRepository.findById(cid).orElseThrow(() -> new IllegalArgumentException("no such data"));
    }

    public CategoryV getVCategory(final Long cid) {
        return categoryVRepository.findById(cid).orElseThrow(() -> new IllegalArgumentException("no such data"));
    }
}
