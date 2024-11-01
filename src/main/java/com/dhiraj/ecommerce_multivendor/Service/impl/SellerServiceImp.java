package com.dhiraj.ecommerce_multivendor.Service.impl;

import java.util.List;

import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.dhiraj.ecommerce_multivendor.Config.JwtProvider;
import com.dhiraj.ecommerce_multivendor.Domin.AccountStatus;
import com.dhiraj.ecommerce_multivendor.Domin.USER_ROLE;
import com.dhiraj.ecommerce_multivendor.Exceptions.SellerException;
import com.dhiraj.ecommerce_multivendor.Modals.Address;
import com.dhiraj.ecommerce_multivendor.Modals.Seller;
import com.dhiraj.ecommerce_multivendor.Repository.AddressRepository;
import com.dhiraj.ecommerce_multivendor.Repository.SellerRepositiory;
import com.dhiraj.ecommerce_multivendor.Service.SellerService;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class SellerServiceImp implements SellerService {

    private final SellerRepositiory sellerRepositiory;
    private final JwtProvider jwtProvider;
    private final PasswordEncoder passwordEncoder;
    private final AddressRepository addressRepository;

    @Override
    public Seller getSellerProfile(String jwt) throws Exception {
        String email = jwtProvider.getEmailFromJwtToken(jwt);
        return this.getSellerByEmail(email);
    }

    @Override
    public Seller createSeller(Seller seller) throws Exception {
        Seller sellerExist = sellerRepositiory.findByEmail(seller.getEmail());
        if (sellerExist != null) {
            throw new Exception("Seller already exist with given email.." + seller.getEmail());
        }
        Address saveAddress = addressRepository.save(seller.getPickupAddress());
        System.out.println("GSTN :- " + seller);
        Seller newSeller = new Seller();

        newSeller.setEmail(seller.getEmail());
        newSeller.setPassword(passwordEncoder.encode(seller.getPassword()));
        newSeller.setSellerName(seller.getSellerName());
        newSeller.setPickupAddress(saveAddress);
        newSeller.setGSTIN(seller.getGSTIN());
        newSeller.setRole(USER_ROLE.ROLE_SELLER);
        newSeller.setMobile(seller.getMobile());
        newSeller.setBankDetails(seller.getBankDetails());
        newSeller.setBusinessDetails(seller.getBusinessDetails());

        return sellerRepositiory.save(newSeller);
    }

    @Override
    public Seller getSellerById(Long id) throws Exception {
        return sellerRepositiory.findById(id)
                .orElseThrow(() -> new SellerException("Seller not found with given id :- " + id));
    }

    @Override
    public Seller getSellerByEmail(String email) throws Exception {
        Seller seller = sellerRepositiory.findByEmail(email);
        if (seller == null)
            throw new Exception("Seller not found with given email.." + email);
        return seller;
    }

    @Override
    public List<Seller> getAllSellers(AccountStatus status) {
        return sellerRepositiory.findByAccountStatus(status);
    }

    @Override
    public Seller updateSeller(Long id, Seller seller) throws Exception {

        Seller sellerExist = this.getSellerById(id);

        if (seller.getSellerName() != null)
            sellerExist.setSellerName(seller.getSellerName());
        if (seller.getMobile() != null)
            sellerExist.setMobile(seller.getMobile());
        if (seller.getEmail() != null)
            sellerExist.setEmail(seller.getEmail());
        if (seller.getBusinessDetails() != null &&
                seller.getBusinessDetails().getBusinessName() != null) {

            sellerExist.getBusinessDetails()
                    .setBusinessName(seller.getBusinessDetails()
                            .getBusinessName());

        }
        if (seller.getBankDetails() != null &&
                seller.getBankDetails().getAccountNumber() != null
                && seller.getBankDetails().getAccountNumber() != null
                && seller.getBankDetails().getIfscCode() != null) {
            sellerExist.getBankDetails()
                    .setIfscCode(seller.getBankDetails()
                            .getIfscCode());

            sellerExist.getBankDetails()
                    .setAccountNumber(seller.getBankDetails()
                            .getAccountNumber());

            sellerExist.getBankDetails()
                    .setAccountHolderName(seller.getBankDetails()
                            .getAccountHolderName());
        }
        if (seller.getPickupAddress() != null
                && seller.getPickupAddress().getAddress() != null
                && seller.getPickupAddress().getCity() != null
                && seller.getPickupAddress().getState() != null
                && seller.getPickupAddress().getMobile() != null) {

            sellerExist.getPickupAddress()
                    .setAddress(seller.getPickupAddress()
                            .getAddress());

            sellerExist.getPickupAddress()
                    .setCity(seller.getPickupAddress()
                            .getCity());

            sellerExist.getPickupAddress()
                    .setState(seller.getPickupAddress()
                            .getState());

            sellerExist.getPickupAddress()
                    .setMobile(seller.getPickupAddress()
                            .getMobile());
        }
        if (seller.getGSTIN() != null)
            sellerExist.setGSTIN(seller.getGSTIN());

        return sellerRepositiory.save(sellerExist);
    }

    @Override
    public void deleteSeller(Long id) throws Exception {
        Seller seller = getSellerById(id);
        sellerRepositiory.delete(seller);
    }

    @Override
    public Seller verifyEmail(String email, String otp) throws Exception {
        Seller seller = getSellerByEmail(email);

        seller.setEmailVerified(true);

        return sellerRepositiory.save(seller);
    }

    @Override
    public Seller updateSellerStatus(Long sellerId, AccountStatus status) throws Exception {
        Seller seller = getSellerById(sellerId);
        seller.setAccountStatus(status);
        return sellerRepositiory.save(seller);
    }

}
