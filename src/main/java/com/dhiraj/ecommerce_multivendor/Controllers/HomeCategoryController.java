package com.dhiraj.ecommerce_multivendor.Controllers;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.dhiraj.ecommerce_multivendor.Modals.Home;
import com.dhiraj.ecommerce_multivendor.Modals.HomeCategory;
import com.dhiraj.ecommerce_multivendor.Service.HomeCategoryService;
import com.dhiraj.ecommerce_multivendor.Service.HomeService;

import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/home")
@RequiredArgsConstructor
public class HomeCategoryController {

    private final HomeCategoryService homeCategoryService;
    private final HomeService homeService;

    @PostMapping("/create")
    public ResponseEntity<Home> createHomeCategories(@RequestBody List<HomeCategory> homeCategories) {

        List<HomeCategory> homeCategory = homeCategoryService.createCategories(homeCategories);
        Home home = homeService.createHomePageData(homeCategory);

        return new ResponseEntity<Home>(home, HttpStatus.ACCEPTED);
    }

    @GetMapping("/admin/home-category")
    public ResponseEntity<List<HomeCategory>> getHomeCategory() throws Exception {
        List<HomeCategory> homeCategory = homeCategoryService.getAllHomeCategories();
        return new ResponseEntity<List<HomeCategory>>(homeCategory, HttpStatus.ACCEPTED);
    }

    @PatchMapping("/admin/home-category/{id}")
    public ResponseEntity<HomeCategory> updateHomeCategory(
            @PathVariable Long id,
            @RequestBody HomeCategory homeCategory) throws Exception {
        HomeCategory updatedCategory = homeCategoryService.updateHomeCategory(homeCategory, id);
        return ResponseEntity.ok(updatedCategory);
    }
}
