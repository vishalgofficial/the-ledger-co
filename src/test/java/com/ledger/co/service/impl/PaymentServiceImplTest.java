package com.ledger.co.service.impl;

import com.ledger.co.domain.PaymentRequest;
import com.ledger.co.repository.LoanRepository;
import com.ledger.co.repository.PaymentRepository;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;

import java.util.List;

import static java.util.Collections.singletonList;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.verify;

@RunWith(MockitoJUnitRunner.class)
public class PaymentServiceImplTest {
    @Mock
    private PaymentRepository paymentRepository;
    @Mock
    private LoanRepository loanRepository;
    @InjectMocks
    private PaymentServiceImpl paymentService;

    @Test
    public void makePayment() {
        paymentService.makePayment(getPaymentRequest());
        verify(paymentRepository).saveAll(any());
        verify(paymentRepository).deleteAll();
        verify(loanRepository).findByBankNameAndBorrowerName(anyString(), anyString());
    }

    private List<PaymentRequest> getPaymentRequest() {
        PaymentRequest paymentRequest = new PaymentRequest();
        paymentRequest.setBankName("das");
        paymentRequest.setBorrowerName("ads");
        paymentRequest.setEmiMonth(1);
        paymentRequest.setLumpsumAmt(1000);
        return singletonList(paymentRequest);
    }
}