package com.ledger.co.service.impl;

import com.ledger.co.domain.LoanRequest;
import com.ledger.co.entity.LoanData;
import com.ledger.co.repository.LoanRepository;
import com.ledger.co.service.LoanService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

import static com.ledger.co.util.CalculationUtil.prepareLoanData;

@Service
@AllArgsConstructor
public class LoanServiceImpl implements LoanService {

    private LoanRepository loanRepository;

    @Override
    @Transactional
    public void createLoan(List<LoanRequest> loanRequest) {
        loanRepository.deleteAll();
        List<LoanData> loans = prepareLoanData(loanRequest);
        loanRepository.saveAll(loans);
    }
}
