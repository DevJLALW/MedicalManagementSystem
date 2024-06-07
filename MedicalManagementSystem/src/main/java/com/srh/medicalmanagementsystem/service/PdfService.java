package com.srh.medicalmanagementsystem.service;

import com.srh.medicalmanagementsystem.entity.Payment;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.thymeleaf.TemplateEngine;
import org.thymeleaf.context.Context;
import com.openhtmltopdf.pdfboxout.PdfRendererBuilder;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.InputStream;
import java.nio.charset.StandardCharsets;
import java.util.Locale;

@Service
public class PdfService {

    @Autowired
    private TemplateEngine templateEngine;

    public InputStream generatePdf(Payment payment) {
        Context context = new Context(Locale.ENGLISH);
        context.setVariable("payment", payment);

        String htmlContent = templateEngine.process("PaymentReceipt", context);

        try (ByteArrayOutputStream os = new ByteArrayOutputStream()) {
            PdfRendererBuilder builder = new PdfRendererBuilder();
            builder.useFastMode();
            builder.withHtmlContent(htmlContent, "/");
            builder.toStream(os);
            builder.run();
            return new ByteArrayInputStream(os.toByteArray());
        } catch (Exception e) {
            throw new RuntimeException("Error generating PDF", e);
        }
    }
}