package com.srh.medicalmanagementsystem.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PharmacyTransactionsRepository extends JpaRepository<PharmacyTransactions, Integer> {
}
