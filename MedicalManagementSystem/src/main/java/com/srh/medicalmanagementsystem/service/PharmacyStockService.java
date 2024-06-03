package com.srh.medicalmanagementsystem.service;

import com.srh.medicalmanagementsystem.dao.PharmacyStockRepository;
import com.srh.medicalmanagementsystem.entity.PharmacyStock;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class PharmacyStockService {

    @Autowired
    PharmacyStockRepository pharmacyStockRepository;

    public List<PharmacyStock> getAllPharmacyStock(){

        List<PharmacyStock> pharmacyStock=new ArrayList<PharmacyStock>();
        pharmacyStockRepository.findAll().forEach(pharmacyStock1 -> pharmacyStock.add(pharmacyStock1));
        return pharmacyStock;
    }

    public PharmacyStock getPharmacyStockById(int id){
        return pharmacyStockRepository.findById(id).get();
    }

    public void saveOrUpdate(PharmacyStock pharmacyStock){
        pharmacyStockRepository.save(pharmacyStock);
    }

    public void delete(int id){

        pharmacyStockRepository.deleteById(id);
    }

    public void update(PharmacyStock pharmacyStock, int pharmacyStockId){
        pharmacyStockRepository.save(pharmacyStock);
    }
}
