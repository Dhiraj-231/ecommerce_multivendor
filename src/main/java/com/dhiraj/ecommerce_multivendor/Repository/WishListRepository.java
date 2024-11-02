package com.dhiraj.ecommerce_multivendor.Repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.dhiraj.ecommerce_multivendor.Modals.Wishlist;

public interface WishListRepository extends JpaRepository<Wishlist, Long> {

    Wishlist findByUserId(Long userId);
}
