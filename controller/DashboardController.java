package com.sd.gmbls.controller;

import com.sd.gmbls.model.CustomerPayment;
import com.sd.gmbls.service.CustomerPaymentService;
import com.sd.gmbls.service.ExportPDFService;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;
import java.math.BigDecimal;
import java.util.List;

@Controller
@RequiredArgsConstructor
public class DashboardController {

    private final CustomerPaymentService paymentService;
    private final ExportPDFService exportPDFService;

    @GetMapping("/dashboard")
    public String dashboard(Model model) {
        List<CustomerPayment> payments = paymentService.getAllPayments();
        int totalWork = paymentService.getTotalWork();
        int totalPipe = paymentService.getTotalPipe();
        BigDecimal totalPaidAtOffice = paymentService.getTotalPaidAtOffice();

        model.addAttribute("payments", payments);
        model.addAttribute("totalWork", totalWork);
        model.addAttribute("totalPipe", totalPipe);
        model.addAttribute("totalPaidAtOffice", totalPaidAtOffice);

        return "dashboard";
    }





    @GetMapping("/dashboard/new")
    public String newPaymentForm(Model model) {
        model.addAttribute("formTitle", "Add New Payment");
        model.addAttribute("formAction", "/dashboard/save");
        model.addAttribute("payment", new CustomerPayment());
        return "payment_form";
    }

    @GetMapping("/dashboard/edit/{id}")
    public String editPaymentForm(@PathVariable Long id, Model model) {
        CustomerPayment payment = paymentService.getById(id);
        model.addAttribute("formTitle", "Edit Payment");
        model.addAttribute("formAction", "/dashboard/save");
        model.addAttribute("payment", payment);
        return "payment_form";
    }

    @PostMapping("/dashboard/save")
    public String savePayment(@ModelAttribute CustomerPayment payment) {
        paymentService.savePayment(payment);
        return "redirect:/dashboard";
    }

    @GetMapping("/dashboard/delete/{id}")
    public String deletePayment(@PathVariable Long id) {
        paymentService.deletePayment(id);
        return "redirect:/dashboard";
    }

    // ✅ New: Search by customer name
    @GetMapping("/dashboard/search/by-name")
    public String searchByName(@RequestParam("name") String name, Model model) {
        List<CustomerPayment> filteredPayments = paymentService.searchByCustomerName(name);
        model.addAttribute("payments", filteredPayments);
        return "dashboard";
    }

    @GetMapping("/dashboard/export/pdf")
    public void exportToPdf(HttpServletResponse response) throws IOException {
        response.setContentType("application/pdf");
        response.setHeader("Content-Disposition", "attachment; filename=customer_payments.pdf");
        exportPDFService.export(response);
    }


}
