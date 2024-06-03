package com.srh.medicalmanagementsystem.dao;

import com.srh.medicalmanagementsystem.entity.PharmacyTransactions;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PharmacyTransactionsRepository extends JpaRepository<PharmacyTransactions, Integer> {
}
