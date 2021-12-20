package com.ledger.co.service;

import com.ledger.co.domain.LoanRequest;

import java.util.List;

public interface LoanService {
    void createLoan(List<LoanRequest> loanRequest);
}
