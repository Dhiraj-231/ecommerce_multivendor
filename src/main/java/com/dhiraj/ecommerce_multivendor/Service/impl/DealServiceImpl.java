package com.dhiraj.ecommerce_multivendor.Service.impl;

import java.util.List;

import org.springframework.stereotype.Service;

import com.dhiraj.ecommerce_multivendor.Modals.Deal;
import com.dhiraj.ecommerce_multivendor.Modals.HomeCategory;
import com.dhiraj.ecommerce_multivendor.Repository.DealRepository;
import com.dhiraj.ecommerce_multivendor.Repository.HomeCategoriesRepository;
import com.dhiraj.ecommerce_multivendor.Service.DealService;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class DealServiceImpl implements DealService {
    private final DealRepository dealRepository;

    private final HomeCategoriesRepository homeCategoryRepository;

    @Override
    public List<Deal> getDeals() {
        return dealRepository.findAll();
    }

    @Override
    public Deal createDeal(Deal deal) {
        HomeCategory category = homeCategoryRepository.findById(deal.getCategory().getId()).get();
        Deal newDeal = dealRepository.save(deal);
        newDeal.setCategory(category);
        newDeal.setDiscount(deal.getDiscount());

        return dealRepository.save(newDeal);
    }

    @Override
    public Deal updateDeal(Deal deal, Long id) throws Exception {

        Deal existingDeal = dealRepository.findById(id).get();
        HomeCategory category = homeCategoryRepository.findById(deal.getCategory().getId()).get();

        if (existingDeal != null) {
            if (deal.getCategory() != null) {
                existingDeal.setCategory(category);
            }
            if (category != null) {
                existingDeal.setCategory(category);
            }
            return dealRepository.save(existingDeal);
        }

        throw new Exception("Deal not found");
    }

    @Override
    public void deleteDeal(Long id) throws Exception {
        Deal deal = dealRepository.findById(id)
                .orElseThrow(() -> new Exception("Deal not found"));
        dealRepository.delete(deal);
    }

}
