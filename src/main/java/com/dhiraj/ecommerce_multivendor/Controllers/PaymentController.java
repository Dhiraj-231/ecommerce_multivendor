package com.dhiraj.ecommerce_multivendor.Controllers;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.dhiraj.ecommerce_multivendor.Modals.Order;
import com.dhiraj.ecommerce_multivendor.Modals.PaymentOrder;
import com.dhiraj.ecommerce_multivendor.Modals.Seller;
import com.dhiraj.ecommerce_multivendor.Modals.SellerReport;
import com.dhiraj.ecommerce_multivendor.Modals.User;
import com.dhiraj.ecommerce_multivendor.Response.ApiResponse;
import com.dhiraj.ecommerce_multivendor.Response.PaymentLinkResponse;
import com.dhiraj.ecommerce_multivendor.Service.PaymentService;
import com.dhiraj.ecommerce_multivendor.Service.SellerReportService;
import com.dhiraj.ecommerce_multivendor.Service.SellerService;
import com.dhiraj.ecommerce_multivendor.Service.UserService;

import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/api/payment")
@RequiredArgsConstructor
public class PaymentController {

    private final PaymentService paymentService;
    private final UserService userService;
    private final SellerService sellerService;
    private final SellerReportService sellerReportService;

    @GetMapping("/api/payment/{paymentId}")
    public ResponseEntity<ApiResponse> paymentSuccessHandler(
            @PathVariable String paymentId,
            @RequestParam String paymentLinkID,
            @RequestHeader("Authorization") String jwt) throws Exception {

        User user = userService.findUserByJwtToken(jwt);
        PaymentLinkResponse paymentLinkResponse;
        PaymentOrder paymentOrder = paymentService.getPaymentOrderByPaymentId(paymentLinkID);
        boolean paymentSuccess = paymentService.ProceedPaymentOrder(
                paymentOrder,
                paymentId,
                paymentLinkID);
        if (paymentSuccess) {
            for (Order order : paymentOrder.getOrders()) {
                // transactionService.createTransaction(order);
                Seller Seller = sellerService.getSellerById(order.getSellerId());
                SellerReport report = sellerReportService.getSellerReport(Seller);
                report.setTotalOrders(report.getTotalOrders() + 1);
                report.setTotalEarnings(report.getTotalEarnings() + order.getTotalSellingPrice());
                report.setTotalSales(report.getTotalSales() + order.getOrderItems().size());
                sellerReportService.updateSellerReport(report);
            }
        }
        ApiResponse response = new ApiResponse();
        response.setMessage("Payment Successful..");
        return new ResponseEntity<>(response, HttpStatus.CREATED);
    }
}
