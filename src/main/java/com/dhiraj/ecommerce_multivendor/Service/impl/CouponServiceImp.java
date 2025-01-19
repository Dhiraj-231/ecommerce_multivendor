package com.dhiraj.ecommerce_multivendor.Service.impl;

import java.time.LocalDate;
import java.util.List;

import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestMapping;

import com.dhiraj.ecommerce_multivendor.Modals.Cart;
import com.dhiraj.ecommerce_multivendor.Modals.Coupon;
import com.dhiraj.ecommerce_multivendor.Modals.User;
import com.dhiraj.ecommerce_multivendor.Repository.CartRepositiory;
import com.dhiraj.ecommerce_multivendor.Repository.CouponRepository;
import com.dhiraj.ecommerce_multivendor.Repository.UserRepo;
import com.dhiraj.ecommerce_multivendor.Service.CouponService;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class CouponServiceImp implements CouponService {

    private final CouponRepository couponRepository;
    private final CartRepositiory cartRepositiory;
    private final UserRepo userRepo;

    @Override
    public Cart applyCoupon(String couponCode, double orderValue, User user) throws Exception {
        Coupon coupon = couponRepository.findByCouponCode(couponCode);
        Cart cart = cartRepositiory.findByUserId(user.getId());

        if (coupon == null) {
            throw new Exception("Coupon not valid..");
        }
        if (user.getUsedCoupons().contains(coupon)) {
            throw new Exception("Coupon already used..");
        }
        if (orderValue < coupon.getMinimumOrderValue()) {
            throw new Exception("Order value is less than minimum order value.." + coupon.getMinimumOrderValue());
        }
        if (coupon.isActive() && LocalDate.now().isAfter(coupon.getValidityEndDate())
                && LocalDate.now().isBefore(coupon.getValidityStartDate())) {
            user.getUsedCoupons().add(coupon);
            userRepo.save(user);

            double discountedPrice = (cart.getTotalSellingPrice() * coupon.getDiscountPercentage()) / 100;
            cart.setTotalSellingPrice(cart.getTotalSellingPrice() - discountedPrice);
            cart.setCouponCode(couponCode);
            cartRepositiory.save(cart);
            return cart;
        }
        throw new Exception("Coupon not valid..");
    }

    @Override
    public Cart removeCoupon(String couponCode, User user) throws Exception {
        Coupon coupon = couponRepository.findByCouponCode(couponCode);
        Cart cart = cartRepositiory.findByUserId(user.getId());
        if (coupon == null) {
            throw new Exception("Coupon not valid..");
        }

        double discountedPrice = (cart.getTotalSellingPrice() * coupon.getDiscountPercentage()) / 100;
        cart.setTotalSellingPrice(cart.getTotalSellingPrice() + discountedPrice);
        cart.setCouponCode(null);

        return cartRepositiory.save(cart);
    }

    @Override
    public Coupon findCouponById(Long couponId) throws Exception {
        return couponRepository.findById(couponId).orElseThrow(() -> new Exception("Coupon not found"));
    }

    @Override
    @PreAuthorize("hasRole('ADMIN')")
    public Coupon createCoupon(Coupon coupon) {
        return couponRepository.save(coupon);
    }

    @Override
    public List<Coupon> findAllCoupons() {
        return couponRepository.findAll();
    }

    @Override
    @PreAuthorize("hasRole('ADMIN')")
    public void deleteCoupon(Long couponId) throws Exception {
        findCouponById(couponId);
        couponRepository.deleteById(couponId);

    }

}
