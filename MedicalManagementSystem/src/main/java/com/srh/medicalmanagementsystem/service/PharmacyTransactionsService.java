package com.srh.medicalmanagementsystem.service;

import com.srh.medicalmanagementsystem.dao.PharmacyTransactionsRepository;
import com.srh.medicalmanagementsystem.entity.PharmacyTransactions;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.ArrayList;
import java.util.List;

public class PharmacyTransactionsService {


    @Autowired
    PharmacyTransactionsRepository pharmacyStockRepository;

    public List<PharmacyTransactions> getAllPharmacyTransactions(){

        List<PharmacyTransactions> pharmacyStock=new ArrayList<PharmacyTransactions>();
        pharmacyStockRepository.findAll().forEach(pharmacyStock1 -> pharmacyStock.add(pharmacyStock1));
        return pharmacyStock;
    }

    public PharmacyTransactions getPharmacyTransactionsById(int id){
        return pharmacyStockRepository.findById(id).get();
    }

    public void saveOrUpdate(PharmacyTransactions pharmacyStock){
        pharmacyStockRepository.save(pharmacyStock);
    }

    public void delete(int id){

        pharmacyStockRepository.deleteById(id);
    }

    public void update(PharmacyTransactions pharmacyStock, int pharmacyStockId){
        pharmacyStockRepository.save(pharmacyStock);
    }
}
