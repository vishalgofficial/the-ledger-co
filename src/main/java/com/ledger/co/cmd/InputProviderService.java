package com.ledger.co.cmd;

import com.ledger.co.domain.BalanceResponse;

import java.io.IOException;
import java.util.List;

public interface InputProviderService {

    List<BalanceResponse> getBalanceData();
    void setup() throws IOException;
}
