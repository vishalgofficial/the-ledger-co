package com.ledger.co.domain;

import lombok.Data;

@Data
public class PaymentRequest {
    private String bankName;
    private String borrowerName;
    private int lumpsumAmt;
    private int emiMonth;
}
