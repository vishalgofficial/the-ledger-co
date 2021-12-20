package com.ledger.co.controller;

import com.ledger.co.domain.BalanceRequest;
import com.ledger.co.domain.BalanceResponse;
import com.ledger.co.domain.LoanRequest;
import com.ledger.co.domain.PaymentRequest;
import com.ledger.co.service.BalanceService;
import com.ledger.co.service.LoanService;
import com.ledger.co.service.PaymentService;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

import static org.springframework.http.HttpStatus.OK;

@SuppressWarnings("ALL")
@RestController
@AllArgsConstructor
@RequestMapping("/loan")
public class LoanController {

    private LoanService loanService;
    private PaymentService paymentService;
    private BalanceService balanceService;

    @PostMapping("/create")
    public ResponseEntity createLoan(@RequestBody List<LoanRequest> loanRequests) {
        loanService.createLoan(loanRequests);
        return new ResponseEntity(OK);
    }

    @PostMapping("/payment")
    public ResponseEntity makePayment(@RequestBody List<PaymentRequest> paymentRequests) {
        paymentService.makePayment(paymentRequests);
        return new ResponseEntity(OK);
    }

    @PostMapping("/balance")
    public ResponseEntity<BalanceResponse> checkBalance(@RequestBody List<BalanceRequest> balanceRequests) {
        return new ResponseEntity(balanceService.checkBalance(balanceRequests), OK);
    }
}
