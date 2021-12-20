package com.ledger.co.cmd;

import com.ledger.co.domain.BalanceRequest;
import com.ledger.co.domain.BalanceResponse;
import com.ledger.co.domain.LoanRequest;
import com.ledger.co.domain.PaymentRequest;
import com.ledger.co.service.BalanceService;
import com.ledger.co.service.LoanService;
import com.ledger.co.service.PaymentService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import static com.ledger.co.cmd.FileUtil.getInputStreamFromFile;
import static com.ledger.co.cmd.FileUtil.getInputStreamFromFileCmd;
import static java.lang.Integer.parseInt;
import static java.util.Arrays.asList;

@Service
@AllArgsConstructor
@Transactional
public class InputProviderImpl implements InputProviderService {

    private LoanService ledgerServiceLoan;
    private PaymentService ledgerServicePayment;
    private BalanceService ledgerServiceBalance;
    private List<LoanRequest> loanRequests;
    private List<PaymentRequest> paymentRequests;
    private List<BalanceRequest> balanceRequests;

    public void setup() throws IOException {
        this.loanRequests = new ArrayList<>();
        this.paymentRequests = new ArrayList<>();
        this.balanceRequests = new ArrayList<>();
        inputProvider(getInputStreamFromFile());
        executeCommandsLoan();
        executeCommandPayments();
    }

    public void setupForCommand(File file) throws IOException {
        this.loanRequests = new ArrayList<>();
        this.paymentRequests = new ArrayList<>();
        this.balanceRequests = new ArrayList<>();
        inputProvider(getInputStreamFromFileCmd(file));
        executeCommandsLoan();
        executeCommandPayments();
    }

    @Override
    public List<BalanceResponse> getBalanceData() {
       return executeCommandsBalance();
    }

    private void executeCommandsLoan() {
        ledgerServiceLoan.createLoan(loanRequests);
    }

    private void executeCommandPayments() {
        ledgerServicePayment.makePayment(paymentRequests);
    }

    private List<BalanceResponse> executeCommandsBalance() {
        return ledgerServiceBalance.checkBalance(balanceRequests);
    }


    public void inputProvider(List<String> inputLines) {
        inputLines.forEach(s -> {
            String[] splitLines = s.split(" ");
            List<String> inputFields = new ArrayList<>(asList(splitLines));
            String command = inputFields.get(0);
            inputFields.remove(0);
            if ("LOAN".equals(command)) {
                prepareLoanCommands(inputFields);
            } else if ("PAYMENT".equals(command)) {
                preparePaymentCommands(inputFields);
            } else if ("BALANCE".equals(command)) {
                prepareBalanceCommands(inputFields);
            }
        });
    }


    private void prepareLoanCommands(List<String> inputFields) {
        LoanRequest loanRequest = new LoanRequest();
        loanRequest.setBankName(inputFields.get(0));
        loanRequest.setBorrowerName(inputFields.get(1));
        loanRequest.setPrincipalAmt(parseInt(inputFields.get(2)));
        loanRequest.setTenure(parseInt(inputFields.get(3)));
        loanRequest.setRoi(parseInt(inputFields.get(4)));
        loanRequests.add(loanRequest);
    }

    private void preparePaymentCommands(List<String> inputFields) {
        PaymentRequest paymentRequest = new PaymentRequest();
        paymentRequest.setBankName(inputFields.get(0));
        paymentRequest.setBorrowerName(inputFields.get(1));
        paymentRequest.setEmiMonth(Integer.parseInt(inputFields.get(2)));
        paymentRequest.setLumpsumAmt(Integer.parseInt(inputFields.get(3)));
        paymentRequests.add(paymentRequest);
    }

    private void prepareBalanceCommands(List<String> inputFields) {
        BalanceRequest balanceRequest = new BalanceRequest();
        balanceRequest.setBankName(inputFields.get(0));
        balanceRequest.setBorrowerName(inputFields.get(1));
        balanceRequest.setEmiNo(Integer.parseInt(inputFields.get(2)));
        balanceRequests.add(balanceRequest);
    }
}
