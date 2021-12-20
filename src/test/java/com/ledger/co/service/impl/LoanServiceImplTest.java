package com.ledger.co.service.impl;

import com.ledger.co.domain.LoanRequest;
import com.ledger.co.repository.LoanRepository;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;

import java.util.List;

import static java.util.Collections.singletonList;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.verify;

@RunWith(MockitoJUnitRunner.class)
public class LoanServiceImplTest {

    @Mock
    private LoanRepository loanRepository;

    @InjectMocks
    private LoanServiceImpl loanService;

    @Test
    public void createLoan() {
        loanService.createLoan(getLoanRequests());
        verify(loanRepository).saveAll(any());
        verify(loanRepository).deleteAll();
    }

    private List<LoanRequest> getLoanRequests() {
        LoanRequest loanRequest = new LoanRequest();
        loanRequest.setBankName("asd");
        loanRequest.setBorrowerName("asd");
        loanRequest.setPrincipalAmt(1000);
        loanRequest.setTenure(1);
        loanRequest.setRoi(1);
        return singletonList(loanRequest);
    }
}