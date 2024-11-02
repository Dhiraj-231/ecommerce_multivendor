package com.dhiraj.ecommerce_multivendor.Service;

import java.util.List;

import com.dhiraj.ecommerce_multivendor.Modals.Home;
import com.dhiraj.ecommerce_multivendor.Modals.HomeCategory;

public interface HomeService {

    Home createHomePageData(List<HomeCategory> homeCategory);
}
