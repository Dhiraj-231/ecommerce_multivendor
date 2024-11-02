package com.dhiraj.ecommerce_multivendor.Service.impl;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.stereotype.Service;

import com.dhiraj.ecommerce_multivendor.Domin.HomeCategorySection;
import com.dhiraj.ecommerce_multivendor.Modals.Deal;
import com.dhiraj.ecommerce_multivendor.Modals.Home;
import com.dhiraj.ecommerce_multivendor.Modals.HomeCategory;
import com.dhiraj.ecommerce_multivendor.Repository.DealRepository;
import com.dhiraj.ecommerce_multivendor.Service.HomeService;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class HomeServiceImpl implements HomeService {

    private final DealRepository dealRepository;

    @Override
    public Home createHomePageData(List<HomeCategory> homeCategory) {
        List<HomeCategory> gridCategories = homeCategory.stream()
                .filter(category -> category.getSection() == HomeCategorySection.GRID).collect(Collectors.toList());
        List<HomeCategory> shopByCategories = homeCategory.stream()
                .filter(category -> category.getSection() == HomeCategorySection.SHOP_BY_CATEGORIES)
                .collect(Collectors.toList());
        List<HomeCategory> electriCategories = homeCategory.stream()
                .filter(category -> category.getSection() == HomeCategorySection.ELECTRIC_CATEGORIES)
                .collect(Collectors.toList());
        List<HomeCategory> dealCategories = homeCategory.stream()
                .filter(category -> category.getSection() == HomeCategorySection.DEALS)
                .collect(Collectors.toList());
        List<Deal> createDeals = new ArrayList<>();
        if (dealRepository.findAll().isEmpty()) {
            List<Deal> deals = homeCategory.stream()
                    .filter(category -> category.getSection() == HomeCategorySection.DEALS)
                    .map(category -> new Deal(null, 10, category)).collect(Collectors.toList());
            createDeals = dealRepository.saveAll(createDeals);
        } else
            createDeals = dealRepository.findAll();

        Home home = new Home();
        home.setGrid(gridCategories);
        home.setShopByCategories(shopByCategories);
        home.setElectronicCategories(electriCategories);
        home.setDeals(createDeals);
        home.setDealCategories(dealCategories);
        return home;
    }

}
