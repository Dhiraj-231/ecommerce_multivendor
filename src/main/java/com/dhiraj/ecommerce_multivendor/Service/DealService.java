package com.dhiraj.ecommerce_multivendor.Service;

import java.util.List;

import com.dhiraj.ecommerce_multivendor.Modals.Deal;

public interface DealService {
    List<Deal> getDeals();

    Deal createDeal(Deal deal);

    Deal updateDeal(Deal deal, Long id) throws Exception;

    void deleteDeal(Long id) throws Exception;
}
