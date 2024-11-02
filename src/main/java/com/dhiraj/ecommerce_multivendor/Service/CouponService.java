package com.dhiraj.ecommerce_multivendor.Service;

import java.util.List;

import com.dhiraj.ecommerce_multivendor.Modals.Cart;
import com.dhiraj.ecommerce_multivendor.Modals.Coupon;
import com.dhiraj.ecommerce_multivendor.Modals.User;

public interface CouponService {

    Cart applyCoupon(String couponCode, double orderValue, User user) throws Exception;

    Cart removeCoupon(String couponCode, User user) throws Exception;

    Coupon findCouponById(Long couponId) throws Exception;

    Coupon createCoupon(Coupon coupon);

    List<Coupon> findAllCoupons();

    void deleteCoupon(Long couponId) throws Exception;
}
