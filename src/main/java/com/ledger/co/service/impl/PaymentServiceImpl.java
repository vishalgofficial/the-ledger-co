package com.ledger.co.service.impl;

import com.ledger.co.domain.PaymentRequest;
import com.ledger.co.entity.LoanData;
import com.ledger.co.entity.PaymentData;
import com.ledger.co.repository.LoanRepository;
import com.ledger.co.repository.PaymentRepository;
import com.ledger.co.service.PaymentService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

@Service
@AllArgsConstructor
public class PaymentServiceImpl implements PaymentService {
    private PaymentRepository paymentRepository;
    private LoanRepository loanRepository;

    @Override
    @Transactional
    public void makePayment(List<PaymentRequest> paymentRequest) {
        paymentRepository.deleteAll();
        paymentRepository.saveAll(preparePaymentData(paymentRequest));
    }

    private List<PaymentData> preparePaymentData(List<PaymentRequest> paymentRequests) {
        List<PaymentData> payments = new ArrayList<>();
        paymentRequests.forEach(payment -> {
            PaymentData paymentData = new PaymentData();
            paymentData.setEmiMonth(payment.getEmiMonth());
            paymentData.setLumpsumAmt(payment.getLumpsumAmt());
            LoanData loan = loanRepository.findByBankNameAndBorrowerName(payment.getBankName(), payment.getBorrowerName());
            paymentData.setLoanData(loan);
            payments.add(paymentData);
        });
        return payments;
    }
}
