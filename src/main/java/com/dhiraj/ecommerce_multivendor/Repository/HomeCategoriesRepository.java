package com.dhiraj.ecommerce_multivendor.Repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.dhiraj.ecommerce_multivendor.Modals.HomeCategory;

public interface HomeCategoriesRepository extends JpaRepository<HomeCategory, Long> {

}
