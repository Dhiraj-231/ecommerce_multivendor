package com.dhiraj.ecommerce_multivendor.Modals;

import java.time.LocalDate;

import jakarta.persistence.Column;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class PaymentInformation {
    @Column(name = "cardholder_name")
    private String cardholderName;

    @Column(name = "card_number")
    private String cardNumber;

    @Column(name = "expiration_date")
    private LocalDate expirationDate;

    @Column(name = "cvv")
    private String cvv;
}
