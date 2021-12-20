package com.ledger.co.domain;

import lombok.Data;

@Data
public class LoanRequest {

    private String bankName;

    private String borrowerName;

    private int principalAmt;

    private int tenure;

    private int roi;
}
