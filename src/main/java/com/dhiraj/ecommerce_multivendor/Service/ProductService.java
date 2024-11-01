package com.dhiraj.ecommerce_multivendor.Service;

import java.util.List;

import org.springframework.data.domain.Page;

import com.dhiraj.ecommerce_multivendor.Exceptions.ProductException;
import com.dhiraj.ecommerce_multivendor.Modals.Product;
import com.dhiraj.ecommerce_multivendor.Modals.Seller;
import com.dhiraj.ecommerce_multivendor.Request.CreateProductRequest;

public interface ProductService {

    public Product createProduct(CreateProductRequest req, Seller seller);

    public void deleteProduct(Long productId);

    public Product updateProduct(Long productId, Product product) throws ProductException;

    public Product findProductById(Long productId) throws ProductException;

    List<Product> searchProduct(String query);

    public Page<Product> getAllProduct(
            String category,
            String brand,
            String colors,
            String sizes,
            Integer minPrice,
            Integer maxPrice,
            Integer minDiscount,
            String sort,
            String stock,
            Integer pageNumber);

    List<Product> getProductBySeller(Long sellerId);

}
