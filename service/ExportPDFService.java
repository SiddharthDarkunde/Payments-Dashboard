package com.sd.gmbls.service;



import com.lowagie.text.*;
import com.lowagie.text.pdf.*;
import com.sd.gmbls.model.CustomerPayment;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import jakarta.servlet.http.HttpServletResponse;
import java.awt.*;
import java.io.IOException;
import java.util.List;
import java.util.stream.Stream;

@Service
@RequiredArgsConstructor
public class ExportPDFService {

    private final CustomerPaymentService paymentService;

    public void export(HttpServletResponse response) throws IOException {
        List<CustomerPayment> payments = paymentService.getAllPayments();

        Document document = new Document();
        PdfWriter.getInstance(document, response.getOutputStream());

        document.open();

        document.add(new Paragraph("Customer Payments Report"));
        document.add(new Paragraph(" ")); // space

        PdfPTable table = new PdfPTable(9); // 9 columns for all fields
        table.setWidthPercentage(100);
        table.setSpacingBefore(10);

        // Headers
        Stream.of(
                "Customer Name", "Village",
                "Machine Used","work","pipe", "Total", "Paid at Machine",
                "Paid at Office",  "Remaining"
        ).forEach(header -> {
            PdfPCell cell = new PdfPCell(new Phrase(header));
            cell.setBackgroundColor(Color.LIGHT_GRAY);
            table.addCell(cell);
        });

        // Rows
        for (CustomerPayment p : payments) {
            table.addCell(p.getCustomerName());
            table.addCell(p.getVillage());

            table.addCell(p.getMachineUsed());
            table.addCell(p.getWork());
            table.addCell(p.getPipe());
            table.addCell(String.valueOf(p.getTotalAmount()));
            table.addCell(String.valueOf(p.getPaidAtMachine()));
            table.addCell(String.valueOf(p.getPaidAtOffice()));

            table.addCell(String.valueOf(p.getRemainingAmount()));
        }

        document.add(table);
        document.close();
    }
}
