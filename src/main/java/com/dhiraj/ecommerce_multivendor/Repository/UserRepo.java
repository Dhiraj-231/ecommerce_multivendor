package com.dhiraj.ecommerce_multivendor.Repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.dhiraj.ecommerce_multivendor.Modals.User;

public interface UserRepo extends JpaRepository<User, Long> {
    User findByEmail(String email);
}