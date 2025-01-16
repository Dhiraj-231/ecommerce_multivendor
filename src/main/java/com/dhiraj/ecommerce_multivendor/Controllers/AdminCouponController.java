package com.dhiraj.ecommerce_multivendor.Controllers;

import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.dhiraj.ecommerce_multivendor.Modals.Cart;
import com.dhiraj.ecommerce_multivendor.Modals.Coupon;
import com.dhiraj.ecommerce_multivendor.Modals.User;
import com.dhiraj.ecommerce_multivendor.Service.CouponService;
import com.dhiraj.ecommerce_multivendor.Service.UserService;

import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/api/coupons")
@RequiredArgsConstructor
public class AdminCouponController {

    private final CouponService couponService;
    private final UserService userService;

    @PostMapping("/apply")
    public ResponseEntity<Cart> applyCoupon(
            @RequestHeader("Authorization") String jwt,
            @RequestParam String apply,
            @RequestParam String couponCode,
            @RequestParam double orderValue) throws Exception {
        User user = userService.findUserByJwtToken(jwt);
        Cart cart;
        if (apply.equals("apply")) {
            cart = couponService.applyCoupon(couponCode, orderValue, user);
        } else {
            cart = couponService.removeCoupon(couponCode, user);
        }

        return ResponseEntity.ok(cart);
    }

    // Admin operation
    @PostMapping("/admin/create")
    public ResponseEntity<Coupon> createCoupon(@RequestBody Coupon coupon) {
        Coupon createdCoupon = couponService.createCoupon(coupon);
        return ResponseEntity.ok(createdCoupon);
    }

    @DeleteMapping("/admin/delete/{id}")
    public ResponseEntity<?> deleteCoupon(@PathVariable Long id) throws Exception {
        couponService.deleteCoupon(id);
        return ResponseEntity.ok("Coupon deleted successfully");
    }

    @GetMapping("/admin/all")
    public ResponseEntity<List<Coupon>> getAllCoupons() {
        List<Coupon> coupons = couponService.findAllCoupons();
        return ResponseEntity.ok(coupons);
    }
}
