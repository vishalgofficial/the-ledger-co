package com.ledger.co.cmd;

import com.ledger.co.domain.BalanceResponse;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.io.IOException;
import java.util.List;

import static org.springframework.http.HttpStatus.OK;

@RestController
@AllArgsConstructor
public class CommandController {
    private InputProviderService inputProviderService;

    @GetMapping(value = "/setup")
    public void setup() throws IOException {
        inputProviderService.setup();
    }

    @GetMapping("/balance")
    public ResponseEntity<List<BalanceResponse>> checkBalance() throws Exception {
        List<BalanceResponse> balanceData = inputProviderService.getBalanceData();
        System.out.println(balanceData.toString());
        return new ResponseEntity<>(balanceData, OK);
    }
}
