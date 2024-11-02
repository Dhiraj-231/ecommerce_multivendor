package com.dhiraj.ecommerce_multivendor.Controllers;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.dhiraj.ecommerce_multivendor.Domin.AccountStatus;
import com.dhiraj.ecommerce_multivendor.Modals.Seller;
import com.dhiraj.ecommerce_multivendor.Service.SellerService;

import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/api/admin")
@RequiredArgsConstructor
public class AdminController {

    private final SellerService sellerService;

    @PatchMapping("/seller/{id}/status/{status}")
    public ResponseEntity<Seller> updateSellersStatus(@PathVariable Long id, @PathVariable AccountStatus status)
            throws Exception {

        Seller updateSeller = sellerService.updateSellerStatus(id, status);
        return ResponseEntity.ok(updateSeller);

    }
}
