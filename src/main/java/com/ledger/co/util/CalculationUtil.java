package com.ledger.co.util;

import com.ledger.co.domain.BalanceResponse;
import com.ledger.co.domain.LoanRequest;
import com.ledger.co.entity.LoanData;
import com.ledger.co.entity.PaymentData;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.List;

import static java.lang.Math.ceil;
import static lombok.AccessLevel.PRIVATE;

@NoArgsConstructor(access = PRIVATE)
public class CalculationUtil {

    public static List<LoanData> prepareLoanData(List<LoanRequest> loanRequest) {
        List<LoanData> loans = new ArrayList<>();
        loanRequest.forEach(loan -> {
            LoanData loanData = new LoanData();
            loanData.setBankName(loan.getBankName());
            loanData.setBorrowerName(loan.getBorrowerName());
            loanData.setPrincipalAmt(loan.getPrincipalAmt());
            int amtToPay = calculateAmountToPay(loan);
            loanData.setAmtToPay(amtToPay);
            loanData.setEmiAmt(calculateEMIAmount(amtToPay, loan.getTenure()));
            loanData.setTenure(loan.getTenure());
            loanData.setRoi(loan.getRoi());
            loans.add(loanData);
        });
        return loans;
    }

    private static int calculateAmountToPay(LoanRequest loanRequest) {
        int principalAmt = loanRequest.getPrincipalAmt();
        return principalAmt + calculateInterestAmount(principalAmt, loanRequest.getTenure(), loanRequest.getRoi());
    }

    private static int calculateEMIAmount(int amtToPay, int tenure) {
        return (int) ceil((double) amtToPay / (12 * tenure));
    }

    private static int calculateInterestAmount(int principleAmount, int years, int interest) {
        return (int) ceil((double) principleAmount * years * interest / 100);
    }

    public static int calculateTotalPaidAmount(int emiNo, int emiAmt) {
        return emiAmt * emiNo;
    }

    public static int calculateFinalPaidAmount(List<PaymentData> payments, int emiNo) {
        return payments.stream().filter(paymentData -> paymentData.getEmiMonth() <= emiNo).mapToInt(PaymentData::getLumpsumAmt).sum();
    }

    public static int calculateEMILeft(LoanData loan, int totalAmountPaid) {
        int amountLeftToPay = loan.getAmtToPay() - totalAmountPaid;
        return (int) ceil((double) amountLeftToPay / loan.getEmiAmt());
    }

    public static BalanceResponse buildBalanceResponse(LoanData loan, int totalAmountPaid, int emiLeft) {
        BalanceResponse balanceResponse = new BalanceResponse();
        balanceResponse.setAmountPaid(totalAmountPaid);
        balanceResponse.setBankName(loan.getBankName());
        balanceResponse.setBorrowerName(loan.getBorrowerName());
        balanceResponse.setEmiLeft(emiLeft);
        return balanceResponse;
    }
}
