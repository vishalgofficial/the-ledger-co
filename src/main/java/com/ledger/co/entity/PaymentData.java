package com.ledger.co.entity;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;

@Getter
@Setter
@Entity
@Table(name = "PAYMENT_DATA")
public class PaymentData {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "paymentId")
    private int paymentId;

    @Column(name = "lumpsumAmt")
    private int lumpsumAmt;

    @Column(name = "emiMonth")
    private int emiMonth;

    @ManyToOne(fetch = FetchType.EAGER
    )
    @JoinColumn(name = "loanNumber", nullable = false)
    private LoanData loanData;

}
