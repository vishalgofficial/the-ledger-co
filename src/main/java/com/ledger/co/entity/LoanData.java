package com.ledger.co.entity;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.util.List;

@Getter
@Setter
@Entity
@Table(name = "LOAN_DATA")
public class LoanData {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "loanNumber")
    private int loanNumber;

    @Column(name = "bankName")
    private String bankName;

    @Column(name = "borrowerName")
    private String borrowerName;

    @Column(name = "principalAmt")
    private int principalAmt;

    @Column(name = "tenure")
    private int tenure;

    @Column(name = "emiAmt")
    private int emiAmt;

    @Column(name = "roi")
    private int roi;

    @Column(name = "amtToPay")
    private int amtToPay;

    @OneToMany(mappedBy = "loanData", cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    private List<PaymentData> payments;

}
