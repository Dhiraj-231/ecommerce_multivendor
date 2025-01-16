package com.dhiraj.ecommerce_multivendor.Controllers;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.dhiraj.ecommerce_multivendor.Modals.Deal;
import com.dhiraj.ecommerce_multivendor.Service.DealService;

import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/admin/deal")
@RequiredArgsConstructor
public class DealController {
    private final DealService dealService;

    @PostMapping()
    public ResponseEntity<Deal> createDeal(@RequestBody Deal deal) {
        Deal newDeal = dealService.createDeal(deal);
        return new ResponseEntity<>(newDeal, HttpStatus.CREATED);
    }

    @PatchMapping("/{id}")
    public ResponseEntity<Deal> updateDeal(@RequestBody Deal deal, @PathVariable Long id) throws Exception {
        Deal updatedDeal = dealService.updateDeal(deal, id);
        return new ResponseEntity<>(updatedDeal, HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String> deleteDeal(@PathVariable Long id) throws Exception {
        dealService.deleteDeal(id);
        return new ResponseEntity<>("Deal deleted successfully", HttpStatus.OK);
    }

    @GetMapping
    public ResponseEntity<List<Deal>> getDeals() {
        List<Deal> deals = dealService.getDeals();

        return new ResponseEntity<>(deals, HttpStatus.ACCEPTED);
    }
}
