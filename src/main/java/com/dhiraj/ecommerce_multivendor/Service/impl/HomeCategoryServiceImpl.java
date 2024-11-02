package com.dhiraj.ecommerce_multivendor.Service.impl;

import java.util.List;

import org.springframework.stereotype.Service;

import com.dhiraj.ecommerce_multivendor.Modals.HomeCategory;
import com.dhiraj.ecommerce_multivendor.Repository.HomeCategoriesRepository;
import com.dhiraj.ecommerce_multivendor.Service.HomeCategoryService;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class HomeCategoryServiceImpl implements HomeCategoryService {

    private final HomeCategoriesRepository homeCategoriesRepository;

    @Override
    public HomeCategory createHomeCategory(HomeCategory homeCategory) {
        return homeCategoriesRepository.save(homeCategory);
    }

    @Override
    public List<HomeCategory> createCategories(List<HomeCategory> homeCategories) {
        if (homeCategoriesRepository.findAll().isEmpty()) {
            return homeCategoriesRepository.saveAll(homeCategories);
        }
        return homeCategoriesRepository.findAll();
    }

    @Override
    public HomeCategory updateHomeCategory(HomeCategory homeCategory, Long id) throws Exception {
        HomeCategory existingHomeCategory = homeCategoriesRepository.findById(id).orElseThrow(() -> new Exception());
        if (homeCategory.getImage() != null) {
            existingHomeCategory.setImage(homeCategory.getImage());
        }
        if (homeCategory.getCategoryId() != null) {
            existingHomeCategory.setCategoryId(homeCategory.getCategoryId());
        }
        return homeCategoriesRepository.save(existingHomeCategory);
    }

    @Override
    public List<HomeCategory> getAllHomeCategories() {
        return homeCategoriesRepository.findAll();
    }

}
