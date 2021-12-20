package com.ledger.co.service;

import com.ledger.co.domain.BalanceRequest;
import com.ledger.co.domain.BalanceResponse;

import java.util.List;

public interface BalanceService {
    List<BalanceResponse> checkBalance(List<BalanceRequest> balanceRequest);
}
