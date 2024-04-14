package com.clg.project.semVI.Ecommerce.Project.Model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Embeddable
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class PaymentDetails {


    private  String paymentMethod;

    private   String status;

    private  String paymentId;

    private  String razorpayPaymentLinkId;

    private  String razorpayPaymentLinkReferenceId;

    private  String razorpayPaymentLinkStatus;

    private  String razorpayPaymentId;
}
