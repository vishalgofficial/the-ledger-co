package com.ledger.co.domain;

import lombok.Data;

@Data
public class BalanceRequest {
    private String bankName;
    private String borrowerName;
    private int emiNo;
}
