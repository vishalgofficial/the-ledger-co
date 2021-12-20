package com.ledger.co.service;

import com.ledger.co.domain.PaymentRequest;

import java.util.List;

public interface PaymentService {
    void makePayment(List<PaymentRequest> paymentRequest);
}
