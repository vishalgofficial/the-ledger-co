package com.ledger.co.service.impl;

import com.ledger.co.domain.BalanceRequest;
import com.ledger.co.domain.BalanceResponse;
import com.ledger.co.entity.LoanData;
import com.ledger.co.entity.PaymentData;
import com.ledger.co.repository.LoanRepository;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.MockitoJUnitRunner;

import java.util.List;

import static java.util.Collections.singletonList;
import static org.junit.Assert.assertEquals;
import static org.mockito.ArgumentMatchers.anyString;

@RunWith(MockitoJUnitRunner.class)
public class BalanceServiceImplTest {
    @Mock
    private LoanRepository loanRepository;

    @InjectMocks
    private BalanceServiceImpl balanceService;

    @Before
    public void setUp() throws Exception {
        Mockito.when(loanRepository.findByBankNameAndBorrowerName(anyString(), anyString())).thenReturn(getMockLoan());
    }

    @Test
    public void checkBalance() {
        List<BalanceResponse> responses = balanceService.checkBalance(getMockBalance());
        BalanceResponse balanceResponse = responses.get(0);
        assertEquals("asd", balanceResponse.getBankName());
        assertEquals("asd", balanceResponse.getBorrowerName());
        assertEquals(1000, balanceResponse.getAmountPaid());
    }

    private LoanData getMockLoan() {
        LoanData loanData = new LoanData();
        loanData.setBankName("asd");
        loanData.setBorrowerName("asd");
        loanData.setPrincipalAmt(1000);
        loanData.setTenure(1);
        loanData.setRoi(1);
        loanData.setAmtToPay(430);
        loanData.setPayments(getPaymentsMock());
        return loanData;
    }

    private List<PaymentData> getPaymentsMock() {
        PaymentData paymentData = new PaymentData();
        paymentData.setEmiMonth(1);
        paymentData.setLumpsumAmt(1000);
        return singletonList(paymentData);
    }

    private List<BalanceRequest> getMockBalance() {
        BalanceRequest balanceRequest = new BalanceRequest();
        balanceRequest.setBankName("ads");
        balanceRequest.setBorrowerName("asd");
        balanceRequest.setEmiNo(2);
        return singletonList(balanceRequest);
    }
}