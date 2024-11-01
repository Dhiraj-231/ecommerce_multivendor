package com.dhiraj.ecommerce_multivendor.Modals;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.OneToOne;
import lombok.*;
import static jakarta.persistence.GenerationType.AUTO;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode
public class SellerReport {

    @Id
    @GeneratedValue(strategy = AUTO)
    private Long id;

    @OneToOne
    private Seller seller;

    private Long totalEarnings = 0L;
    private Long totalSales = 0L;
    private Long totalOrders = 0L;
    private Long totalRefunds = 0L;
    private Long totalTax = 0L;
    private Long netEarnings = 0l;
    private Long totalOrder = 0L;
    private Long cancelledOrders = 0L;
    private Long totalTransactions = 0L;
}
