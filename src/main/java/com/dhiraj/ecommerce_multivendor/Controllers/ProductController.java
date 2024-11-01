package com.dhiraj.ecommerce_multivendor.Controllers;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.dhiraj.ecommerce_multivendor.Exceptions.ProductException;
import com.dhiraj.ecommerce_multivendor.Modals.Product;
import com.dhiraj.ecommerce_multivendor.Service.ProductService;

import lombok.RequiredArgsConstructor;

@Controller
@RequestMapping("/products")
@RequiredArgsConstructor
public class ProductController {

    private final ProductService productService;

    @GetMapping("/{productId}")
    public ResponseEntity<Product> getProductById(@PathVariable Long productId)
            throws ProductException {
        Product product = productService.findProductById(productId);
        return ResponseEntity.ok(product);
    }

    @GetMapping("/search")
    public ResponseEntity<List<Product>> searchProducts(@RequestParam("query") String query) {
        List<Product> products = productService.searchProduct(query);
        return ResponseEntity.ok(products);
    }

    @GetMapping()
    public ResponseEntity<Page<Product>> getAllProducts(
            @RequestParam(value = "pageNumber", defaultValue = "0") Integer pageNumber,
            @RequestParam(required = false, value = "category") String category,
            @RequestParam(required = false, value = "minPrice") Integer minPrice,
            @RequestParam(required = false, value = "maxPrice") Integer maxPrice,
            @RequestParam(required = false, value = "sort") String sort,
            @RequestParam(required = false, value = "stock") Integer stock,
            @RequestParam(required = false, value = "minDiscount") Integer minDiscount,
            @RequestParam(required = false, value = "size") String size,
            @RequestParam(required = false, value = "brand") String brand,
            @RequestParam(required = false, value = "color") String color) {

        return new ResponseEntity<>(productService.getAllProduct(
                category,
                brand,
                color,
                size,
                minPrice, maxPrice,
                minDiscount, sort, sort, pageNumber), HttpStatus.OK);
    }

}
