package com.ledger.co.repository;

import com.ledger.co.entity.LoanData;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface LoanRepository extends JpaRepository<LoanData, Integer> {
    LoanData findByBankNameAndBorrowerName(String bankName, String borrowerName);
}
