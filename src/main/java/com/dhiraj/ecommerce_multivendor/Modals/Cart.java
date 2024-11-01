package com.dhiraj.ecommerce_multivendor.Modals;

import java.util.HashSet;
import java.util.Set;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import static jakarta.persistence.GenerationType.AUTO;
import jakarta.persistence.OneToMany;
import jakarta.persistence.OneToOne;

/**
 * Represents a shopping cart for a user in an e-commerce multi-vendor application.
 * The cart contains a set of cart items, and tracks the total selling price, total number of items, total MRP price, and any applied discount and coupon code.
 */
@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode
public class Cart {
    @Id
    @GeneratedValue(strategy = AUTO)
    private Long id;

    @OneToOne
    private User user;
    @OneToMany(mappedBy = "cart", cascade = CascadeType.ALL, orphanRemoval = true)
    private Set<CartItem> carItems = new HashSet<>();
    private double totalSellingPrice;
    private int totalItem;
    private int totalMrpPrice;
    private int discount;
    private String couponCode;
}
