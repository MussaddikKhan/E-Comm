package com.clg.project.semVI.Ecommerce.Project.Model;

import jakarta.persistence.Column;

import java.time.LocalDate;


public class PaymentInformation {
    @Column(name = "cardholder_name")
    private String cardholderName;
    @Column(name = "card_name")
    private String cardNumber;
    @Column(name = "expiration_date")
    private LocalDate expirationDate;
    private String cvv;
}
