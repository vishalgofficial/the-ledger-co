package com.ledger.co.service.impl;

import com.ledger.co.domain.BalanceRequest;
import com.ledger.co.domain.BalanceResponse;
import com.ledger.co.entity.LoanData;
import com.ledger.co.repository.LoanRepository;
import com.ledger.co.service.BalanceService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

import static com.ledger.co.util.CalculationUtil.*;

@Service
@AllArgsConstructor
public class BalanceServiceImpl implements BalanceService {
    private LoanRepository loanRepository;

    @Override
    public List<BalanceResponse> checkBalance(List<BalanceRequest> balanceRequests) {
        List<BalanceResponse> responses = new ArrayList<>();
        balanceRequests.forEach(balanceRequest -> {
            LoanData loan = loanRepository.findByBankNameAndBorrowerName(balanceRequest.getBankName(), balanceRequest.getBorrowerName());
            int emiNo = balanceRequest.getEmiNo();
            int totalAmountPaid = calculateTotalPaidAmount(emiNo, loan.getEmiAmt()) + calculateFinalPaidAmount(loan.getPayments(), emiNo);
            int emiLeft = calculateEMILeft(loan, totalAmountPaid);
            responses.add(buildBalanceResponse(loan, totalAmountPaid, emiLeft));
        });
        return responses;
    }
}
