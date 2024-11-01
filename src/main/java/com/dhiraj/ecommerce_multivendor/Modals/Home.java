package com.dhiraj.ecommerce_multivendor.Modals;

import java.util.List;

import lombok.Data;

@Data
public class Home {

    private List<HomeCategory> grid;
    private List<HomeCategory> shopByCategories;
    private List<HomeCategory> electronicCategories;
    private List<HomeCategory> dealCategories;
    private List<Deal> deals;
}
