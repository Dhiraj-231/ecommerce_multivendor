package com.dhiraj.ecommerce_multivendor.Controllers;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.dhiraj.ecommerce_multivendor.Exceptions.ProductException;
import com.dhiraj.ecommerce_multivendor.Modals.Product;
import com.dhiraj.ecommerce_multivendor.Modals.Seller;
import com.dhiraj.ecommerce_multivendor.Request.CreateProductRequest;
import com.dhiraj.ecommerce_multivendor.Service.ProductService;
import com.dhiraj.ecommerce_multivendor.Service.SellerService;

import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/seller/product")
@RequiredArgsConstructor
public class SellerProductController {
    private final ProductService productService;
    private final SellerService sellerService;

    @GetMapping()
    public ResponseEntity<List<Product>> getProductBySellerId(@RequestHeader("Authorization") String token)
            throws Exception {
        Seller seller = sellerService.getSellerProfile(token);

        return ResponseEntity.ok(productService.getProductBySeller(seller.getId()));
    }

    @PostMapping("/create")
    public ResponseEntity<Product> createProduct(
            @RequestBody CreateProductRequest request,

            @RequestHeader("Authorization") String jwt)
            throws Exception {

        System.out.println("Error " + jwt);

        Seller seller = sellerService.getSellerProfile(jwt);
        Product product = productService.createProduct(request, seller);

        return new ResponseEntity<>(product, HttpStatus.CREATED);
    }

    @DeleteMapping("/{productId}")
    public ResponseEntity<Void> deleteProduct(@PathVariable Long productId) throws ProductException {
        productService.deleteProduct(productId);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @PutMapping("/{productId}")
    public ResponseEntity<Product> updateProduct(
            @PathVariable Long productId,
            @RequestBody Product product) throws ProductException {

        Product updateProduct = productService.updateProduct(productId, product);

        return new ResponseEntity<>(updateProduct, HttpStatus.OK);
    }
     @PatchMapping("/{productId}/stock")
    public ResponseEntity<Product> updateProductStock(@PathVariable Long productId) {
        try {
            Product updatedProduct = productService.updateProductStock(productId);
            return new ResponseEntity<>(updatedProduct, HttpStatus.OK);
        } catch (ProductException e) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }
}
