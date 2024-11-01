package com.dhiraj.ecommerce_multivendor.Repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.dhiraj.ecommerce_multivendor.Domin.AccountStatus;
import com.dhiraj.ecommerce_multivendor.Modals.Seller;

public interface SellerRepositiory extends JpaRepository<Seller, Long> {

    Seller findByEmail(String email);

    List<Seller> findByAccountStatus(AccountStatus accountStatus);
}
