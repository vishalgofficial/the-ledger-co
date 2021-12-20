package com.ledger.co.repository;

import com.ledger.co.entity.PaymentData;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PaymentRepository extends JpaRepository<PaymentData, Long> {


}