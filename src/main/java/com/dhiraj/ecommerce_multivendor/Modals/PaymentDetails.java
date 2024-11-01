package com.dhiraj.ecommerce_multivendor.Modals;

import com.dhiraj.ecommerce_multivendor.Domin.PaymentStatus;

import jakarta.persistence.Column;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class PaymentDetails {

    private String paymentId;
    private String razorpayPaymentLinkId;
    private String razorpayPaymentLinkReferenceId;
    private String razorpayPaymentLinkStatus;
    private String razorpayPaymentId;
    @Column(name = "payment_status_details")
    private PaymentStatus paymentStatus;
}
