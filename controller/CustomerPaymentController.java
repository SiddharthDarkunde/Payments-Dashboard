package com.sd.gmbls.controller;

import com.lowagie.text.*;
import com.lowagie.text.pdf.PdfWriter;
import com.sd.gmbls.model.CustomerPayment;
import com.sd.gmbls.service.CustomerPaymentService;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;
import com.lowagie.text.pdf.PdfPTable;
import com.lowagie.text.pdf.PdfWriter;
import com.lowagie.text.pdf.PdfPCell;

import java.awt.*;
import java.io.IOException;
import java.time.LocalDate;
import java.util.List;
import java.util.stream.Stream;

@RestController
@RequestMapping("/api/payments")
@RequiredArgsConstructor
@CrossOrigin("*")
public class CustomerPaymentController {

    private final CustomerPaymentService service;

    @GetMapping
    public List<CustomerPayment> getAll() {
        return service.getAllPayments();
    }

    @GetMapping("/{id}")
    public CustomerPayment getById(@PathVariable Long id) {
        return service.getById(id);
    }

    @PostMapping
    public CustomerPayment create(@RequestBody CustomerPayment payment) {
        return service.savePayment(payment);
    }

    @PutMapping("/{id}")
    public CustomerPayment update(@PathVariable Long id, @RequestBody CustomerPayment updatedPayment) {
        updatedPayment.setId(id);
        return service.savePayment(updatedPayment);
    }

    @DeleteMapping("/{id}")
    public void delete(@PathVariable Long id) {
        service.deletePayment(id);
    }





}
