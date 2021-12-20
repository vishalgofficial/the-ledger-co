package com.ledger.co.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.ledger.co.domain.BalanceRequest;
import com.ledger.co.domain.LoanRequest;
import com.ledger.co.domain.PaymentRequest;
import com.ledger.co.service.BalanceService;
import com.ledger.co.service.LoanService;
import com.ledger.co.service.PaymentService;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.MockitoJUnitRunner;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import java.util.List;

import static com.ledger.co.util.FileUtil.buildMockRequest;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

@RunWith(MockitoJUnitRunner.class)
public class LoanControllerTest {

    @Mock
    private LoanController loanController;
    @Mock
    private LoanService loanService;
    @Mock
    private PaymentService paymentService;
    @Mock
    private BalanceService balanceService;
    @InjectMocks
    private LoanController controller;

    private MockMvc mockMvc;
    private ObjectMapper mapper;
    private List<LoanRequest> mockLoanRequests;
    private List<PaymentRequest> paymentRequests;
    private List<BalanceRequest> balanceRequests;


    @Before
    public void setUp() {
        mapper = new ObjectMapper();
        mockMvc = MockMvcBuilders.standaloneSetup(loanController).build();
    }

    @Test
    public void createLoan_ApiCheck() throws Exception {
        mockLoanRequests = (List<LoanRequest>) buildMockRequest("loan.json");
        mockMvc.perform(MockMvcRequestBuilders.post("/loan/create")
                .content(mapper.writeValueAsString(mockLoanRequests))
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(MockMvcResultMatchers.status().isOk());
    }

    @Test
    public void makePayment_ApiCheck() throws Exception {
        paymentRequests = (List<PaymentRequest>) buildMockRequest("payment.json");
        mockMvc.perform(MockMvcRequestBuilders.post("/loan/payment")
                .content(mapper.writeValueAsString(paymentRequests))
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(MockMvcResultMatchers.status().isOk());
    }

    @Test
    public void checkBalance_ApiCheck() throws Exception {
        balanceRequests = (List<BalanceRequest>) buildMockRequest("payment.json");
        mockMvc.perform(MockMvcRequestBuilders.post("/loan/payment")
                .content(mapper.writeValueAsString(balanceRequests))
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(MockMvcResultMatchers.status().isOk());
    }

    @Test
    public void createLoan() {
        controller.createLoan(mockLoanRequests);
        verify(loanService).createLoan(any());
    }

    @Test
    public void makePayment() {
        controller.makePayment(paymentRequests);
        verify(paymentService).makePayment(any());
    }

    @Test
    public void balanceCheck() {
        controller.checkBalance(balanceRequests);
        verify(balanceService).checkBalance(any());
    }

}