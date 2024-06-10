package com.srh.medicalmanagementsystem.service;

import com.srh.medicalmanagementsystem.entity.PharmacyPrescription;
import com.srh.medicalmanagementsystem.entity.PharmacyInventory;
import com.srh.medicalmanagementsystem.dao.PharmacyPrescriptionRepository;
import com.srh.medicalmanagementsystem.dao.PharmacyInventoryRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.List;

@Service
public class PharmacyPrescriptionService {

    @Autowired
    private PharmacyPrescriptionRepository prescriptionRepository;

    @Autowired
    private PharmacyInventoryRepository inventoryRepository;

    public List<PharmacyPrescription> getAllPrescriptions() {
        return prescriptionRepository.findAll();
    }

    public PharmacyPrescription getPrescriptionById(int id) {
        return prescriptionRepository.findById(id).orElseThrow(() -> new RuntimeException("Prescription not found"));
    }

    public PharmacyPrescription savePrescription(PharmacyPrescription prescription) {
        calculateTotalBill(prescription);
        return prescriptionRepository.save(prescription);
    }

    public void deletePrescription(int id) {
        prescriptionRepository.deleteById(id);
    }

    private void calculateTotalBill(PharmacyPrescription prescription) {
        PharmacyInventory inventory = inventoryRepository.findById(prescription.getInventory().getInventoryID())
                .orElseThrow(() -> new RuntimeException("Inventory not found"));
        BigDecimal medicinePrice = inventory.getMedicinePrice();
        int dosage = prescription.getMedicineDosage();
        int days = prescription.getNumberOfDays();
        BigDecimal totalBill = medicinePrice.multiply(BigDecimal.valueOf(dosage)).multiply(BigDecimal.valueOf(days));
        prescription.setMedicinePrice(medicinePrice);
        prescription.setTotalBill(totalBill);
    }
}
