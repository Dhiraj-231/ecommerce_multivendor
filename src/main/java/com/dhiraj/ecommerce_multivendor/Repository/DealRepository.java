package com.dhiraj.ecommerce_multivendor.Repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.dhiraj.ecommerce_multivendor.Modals.Deal;

public interface DealRepository extends JpaRepository<Deal, Long> {

}
