package com.srh.medicalmanagementsystem.controller;

import com.srh.medicalmanagementsystem.service.PdfService;
import com.srh.medicalmanagementsystem.service.RoomService;
import com.srh.medicalmanagementsystem.service.PharmacyPrescriptionService;
import com.srh.medicalmanagementsystem.entity.Payment;
import com.srh.medicalmanagementsystem.service.PaymentService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.io.InputStream;
import java.math.BigDecimal;
import java.util.List;

@Controller
public class PaymentController {

    @Autowired
    private PaymentService paymentService;

    @Autowired
    private PdfService pdfService;

    @Autowired
    private PharmacyPrescriptionService pharmacyPrescriptionService;

    @Autowired
    private RoomService roomService;

    @GetMapping("/payments")
    public String listPayments(Model model) {
        List<Payment> payments = paymentService.getAllPayments();
        model.addAttribute("payments", payments);
        return "/patients/ShowPayments";
    }

    @GetMapping("/payments/add")
    public String showAddPaymentForm(Model model) {
        model.addAttribute("payment", new Payment());
        return "/patients/NewPayment";
    }

    @PostMapping("/payments/add")
    public String savePayment(@ModelAttribute Payment payment) {
        paymentService.savePayment(payment);
        return "redirect:/payments";
    }

    @GetMapping("/payments/receipt/{id}")
    public void generateReceipt(@PathVariable Long id, HttpServletResponse response) {
        Payment payment = paymentService.getPaymentById(id);
        InputStream pdfStream = pdfService.generatePdf(payment);

        response.setContentType("application/pdf");
        response.setHeader("Content-Disposition", "attachment; filename=receipt.pdf");

        try {
            org.apache.commons.io.IOUtils.copy(pdfStream, response.getOutputStream());
            response.getOutputStream().flush();
        } catch (Exception e) {
            throw new RuntimeException("Error writing PDF to response", e);
        }
    }

    @GetMapping("/pharmacy-bill/{patientId}")
    @ResponseBody
    public BigDecimal getPharmacyBill(@PathVariable int patientId) {
        BigDecimal pharmacyBill = pharmacyPrescriptionService.getPharmacyBillByPatientId(patientId);
        return pharmacyBill != null ? pharmacyBill : BigDecimal.ZERO;
    }

    @GetMapping("/room-bill/{patientId}")
    @ResponseBody
    public BigDecimal getRoomBill(@PathVariable int patientId) {
        BigDecimal roomBill = roomService.getRoomBillByPatientId(patientId);
        return roomBill != null ? roomBill : BigDecimal.ZERO;
    }
}
