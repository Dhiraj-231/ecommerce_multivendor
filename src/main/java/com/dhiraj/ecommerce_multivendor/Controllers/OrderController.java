package com.dhiraj.ecommerce_multivendor.Controllers;

import java.util.List;
import java.util.Set;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.dhiraj.ecommerce_multivendor.Domin.PaymentMethod;
import com.dhiraj.ecommerce_multivendor.Modals.Address;
import com.dhiraj.ecommerce_multivendor.Modals.Cart;
import com.dhiraj.ecommerce_multivendor.Modals.Order;
import com.dhiraj.ecommerce_multivendor.Modals.OrderItem;
import com.dhiraj.ecommerce_multivendor.Modals.PaymentOrder;
import com.dhiraj.ecommerce_multivendor.Modals.Seller;
import com.dhiraj.ecommerce_multivendor.Modals.SellerReport;
import com.dhiraj.ecommerce_multivendor.Modals.User;
import com.dhiraj.ecommerce_multivendor.Repository.PaymentOrderRepository;
import com.dhiraj.ecommerce_multivendor.Response.PaymentLinkResponse;
import com.dhiraj.ecommerce_multivendor.Service.CartService;
import com.dhiraj.ecommerce_multivendor.Service.OrderService;
import com.dhiraj.ecommerce_multivendor.Service.PaymentService;
import com.dhiraj.ecommerce_multivendor.Service.SellerReportService;
import com.dhiraj.ecommerce_multivendor.Service.SellerService;
import com.dhiraj.ecommerce_multivendor.Service.UserService;
import com.razorpay.PaymentLink;

import lombok.RequiredArgsConstructor;

@Controller
@RequiredArgsConstructor
@RequestMapping("/orders")
public class OrderController {

    private final OrderService orderService;
    private final UserService userService;
    private final CartService cartService;
    private final SellerService sellerService;
    private final SellerReportService sellerReportService;
    private final PaymentService paymentService;
    private final PaymentOrderRepository paymentOrderRepository;

    @PostMapping("/create")
    public ResponseEntity<PaymentLinkResponse> createOrderHandler(
            @RequestBody Address spippingAddress,
            @RequestParam PaymentMethod paymentMethod,
            @RequestHeader("Authorization") String jwt) throws Exception {

        User user = userService.findUserByJwtToken(jwt);
        Cart cart = cartService.finduserCart(user);

        Set<Order> orders = orderService.createOrder(user, spippingAddress, cart);
        PaymentOrder paymentOrder = paymentService.createOrder(user, orders);
        PaymentLinkResponse res = new PaymentLinkResponse();
        if (paymentMethod.equals(PaymentMethod.RAZORPAY)) {
            PaymentLink payment = paymentService.createRazorPayPaymentLink(
                    user,
                    paymentOrder.getAmount(),
                    paymentOrder.getId());
            String paymentUrl = payment.get("short_url");
            String paymentUrlId = payment.get("id");
            res.setPayment_link_url(paymentUrl);

            paymentOrder.setPaymentLinkId(paymentUrlId);
            paymentOrderRepository.save(paymentOrder);

        } else {
            String paymentUrl = paymentService.createStripePaymentLink(
                    user,
                    paymentOrder.getAmount(),
                    paymentOrder.getId());

            res.setPayment_link_url(paymentUrl);
        }
        return new ResponseEntity<>(res, HttpStatus.CREATED);
    }

    @GetMapping("/orderHistory")
    public ResponseEntity<List<Order>> usersOrderHistoryHandler(@RequestHeader("Authorization") String jwt)
            throws Exception {
        User user = userService.findUserByJwtToken(jwt);

        List<Order> orders = orderService.usersOrderHistory(user.getId());

        return new ResponseEntity<>(orders, HttpStatus.ACCEPTED);
    }

    @GetMapping("/user/order/{orderId}")
    public ResponseEntity<Order> getOrderByIdHandler(@RequestHeader("Authorization") String jwt,
            @PathVariable Long orderId) throws Exception {
        User user = userService.findUserByJwtToken(jwt);
        Order orderDetails = orderService.findOrderById(orderId);
        return new ResponseEntity<>(orderDetails, HttpStatus.ACCEPTED);
    }

    @GetMapping("/item/{orderItemId}")
    public ResponseEntity<OrderItem> getOrderItemByIdHandler(@RequestHeader("Authorization") String jwt,
            @PathVariable Long orderItemId) throws Exception {
        User user = userService.findUserByJwtToken(jwt);
        OrderItem orderItem = orderService.findOrderItemById(orderItemId);
        return new ResponseEntity<>(orderItem, HttpStatus.ACCEPTED);
    }

    @PutMapping("/{orderId}/cancel")
    public ResponseEntity<String> cancelOrderHandler(@RequestHeader("Authorization") String jwt,
            @PathVariable Long orderId) throws Exception {
        User user = userService.findUserByJwtToken(jwt);
        Order order = orderService.cancelOrder(orderId, user);
        Seller seller = sellerService.getSellerById(order.getSellerId());
        SellerReport sellerReport = sellerReportService.getSellerReport(seller);

        sellerReport.setCancelledOrders(sellerReport.getCancelledOrders() + 1);
        sellerReport.setTotalRefunds(sellerReport.getTotalRefunds() +
                order.getTotalSellingPrice());
        sellerReportService.updateSellerReport(sellerReport);
        return new ResponseEntity<>("Order Cancelled Successfully..", HttpStatus.ACCEPTED);
    }

}
