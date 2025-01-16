package com.dhiraj.ecommerce_multivendor.Repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.dhiraj.ecommerce_multivendor.Domin.PayoutsStatus;
import com.dhiraj.ecommerce_multivendor.Modals.Payouts;

public interface PayoutsRepository extends JpaRepository<Payouts, Long> {
    List<Payouts> findPayoutsBySellerId(Long sellerId);

    List<Payouts> findAllByStatus(PayoutsStatus status);
}
