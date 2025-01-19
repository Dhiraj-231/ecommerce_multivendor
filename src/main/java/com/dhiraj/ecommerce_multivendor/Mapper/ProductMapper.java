package com.dhiraj.ecommerce_multivendor.Mapper;

import com.dhiraj.ecommerce_multivendor.Modals.Product;
import com.dhiraj.ecommerce_multivendor.dto.ProductDto;

public class ProductMapper {
    public static ProductDto toProductDto(Product product) {
        ProductDto productDto = new ProductDto();
        productDto.setId(product.getId());
        productDto.setTitle(product.getTitle());
        productDto.setDescription(product.getDescription());
        productDto.setMrpPrice(product.getMrpPrice());
        productDto.setSellingPrice(product.getSellingPrice());
        productDto.setDiscountPercent(product.getDiscountPercent());
        productDto.setQuantity(product.getQuantity());
        productDto.setColor(product.getColor());
        productDto.setImages(product.getImages());
        productDto.setNumRatings(product.getNumRatings());
        productDto.setCreatedAt(product.getCreateAt());
        productDto.setSizes(product.getSizes());

        return productDto;
    }
    public Product mapToEntity(ProductDto productDto) {
        return null;
    }
}
