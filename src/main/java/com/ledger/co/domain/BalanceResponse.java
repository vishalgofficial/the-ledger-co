package com.ledger.co.domain;

import lombok.Data;

@Data
public class BalanceResponse {
    private String bankName;
    private String borrowerName;
    private int amountPaid;
    private int emiLeft;

    @Override
    public String toString() {
        return bankName + " " + borrowerName + " " + amountPaid + " " + emiLeft;
    }
}
