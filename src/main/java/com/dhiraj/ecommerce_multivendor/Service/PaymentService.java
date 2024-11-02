package com.dhiraj.ecommerce_multivendor.Service;

import java.util.Set;

import com.dhiraj.ecommerce_multivendor.Modals.Order;
import com.dhiraj.ecommerce_multivendor.Modals.PaymentOrder;
import com.dhiraj.ecommerce_multivendor.Modals.User;
import com.razorpay.PaymentLink;
import com.razorpay.RazorpayException;
import com.stripe.exception.StripeException;

public interface PaymentService {

    PaymentOrder createOrder(User user, Set<Order> orders);

    PaymentOrder getPaymentOrderById(Long orderId) throws Exception;

    PaymentOrder getPaymentOrderByPaymentId(String orderId) throws Exception;

    Boolean ProceedPaymentOrder(PaymentOrder paymentOrder, String paymentId, String paymentLinkID)
            throws RazorpayException;

    PaymentLink createRazorPayPaymentLink(User user, Long amount, Long orderId) throws RazorpayException;

    String createStripePaymentLink(User user, Long amount, Long orderId) throws StripeException;

}
