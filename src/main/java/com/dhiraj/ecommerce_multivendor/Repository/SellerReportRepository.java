package com.dhiraj.ecommerce_multivendor.Repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.dhiraj.ecommerce_multivendor.Modals.SellerReport;

public interface SellerReportRepository extends JpaRepository<SellerReport, Long> {

    SellerReport findBySellerId(Long sellerId);
}
