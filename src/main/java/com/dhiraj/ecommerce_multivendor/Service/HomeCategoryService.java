package com.dhiraj.ecommerce_multivendor.Service;

import java.util.List;

import com.dhiraj.ecommerce_multivendor.Modals.HomeCategory;

public interface HomeCategoryService {

    HomeCategory createHomeCategory(HomeCategory homeCategory);

    List<HomeCategory> createCategories(List<HomeCategory> homeCategories);

    HomeCategory updateHomeCategory(HomeCategory homeCategory, Long id) throws Exception;

    List<HomeCategory> getAllHomeCategories();

}
