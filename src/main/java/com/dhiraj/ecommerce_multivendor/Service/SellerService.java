package com.dhiraj.ecommerce_multivendor.Service;

import java.util.List;

import com.dhiraj.ecommerce_multivendor.Domin.AccountStatus;
import com.dhiraj.ecommerce_multivendor.Modals.Seller;

public interface SellerService {

    Seller getSellerProfile(String jwt) throws Exception;

    Seller createSeller(Seller seller) throws Exception;

    Seller getSellerById(Long id) throws Exception;

    Seller getSellerByEmail(String email) throws Exception;

    List<Seller> getAllSellers(AccountStatus status);

    Seller updateSeller(Long id, Seller seller) throws Exception;

    void deleteSeller(Long id) throws Exception;

    Seller verifyEmail(String email, String otp) throws Exception;

    Seller updateSellerStatus(Long sellerId, AccountStatus status) throws Exception;

}
