package com.dhiraj.ecommerce_multivendor.Repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.dhiraj.ecommerce_multivendor.Modals.VerificationCode;

public interface VerificationCodeRepositiory extends JpaRepository<VerificationCode, Long> {

    VerificationCode findByemail(String email);

    VerificationCode findByOtp(String otp);
}
