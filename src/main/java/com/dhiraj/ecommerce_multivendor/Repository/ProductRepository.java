package com.dhiraj.ecommerce_multivendor.Repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.dhiraj.ecommerce_multivendor.Modals.Product;

public interface ProductRepository extends JpaRepository<Product, Long>, JpaSpecificationExecutor<Product> {

    List<Product> findBySellerId(Long sellerId);

    @Query("SELECT p FROM Product p WHERE (:query is null or lower(p.title)" +
            "Like lower(concat('%',:query,'%')))" +
            "or (:query is null or lower(p.category.name))" +
            "Like lower(concat('%',:query,'%')))")

    List<Product> searchProductByQuery(@Param("query") String query);

}
