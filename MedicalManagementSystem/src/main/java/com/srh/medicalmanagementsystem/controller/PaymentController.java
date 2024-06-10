package com.srh.medicalmanagementsystem.controller;

import com.srh.medicalmanagementsystem.service.PdfService;
import com.srh.medicalmanagementsystem.entity.Payment;
import com.srh.medicalmanagementsystem.service.PaymentService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

import jakarta.servlet.http.HttpServletResponse;
import java.io.InputStream;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import java.util.List;

@Controller
public class PaymentController {

    @Autowired
    private PaymentService paymentService;

    @Autowired
    private PdfService pdfService;

    @GetMapping("/patients/payments")
    public String listPayments(Model model) {
        List<Payment> payments = paymentService.getAllPayments();
        model.addAttribute("payments", payments);
        return "/patients/ShowPayments";
    }

    @GetMapping("/patients/payments/add")
    public String showAddPaymentForm(Model model) {
        model.addAttribute("payment", new Payment());
        return "/patients/payments";
    }

    @PostMapping("/patients/payments")
    public String savePayment(@ModelAttribute Payment payment) {
        paymentService.savePayment(payment);
        return "redirect:/payments";
    }

    @PostMapping("/patients/payments/add")
    public String addPayment(@ModelAttribute Payment payment) {
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
}