package com.sd.gmbls.service;




import com.sd.gmbls.model.CustomerPayment;
import com.sd.gmbls.repo.CustomerPaymentRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.List;

@Service
@RequiredArgsConstructor
public class CustomerPaymentService {

    private final CustomerPaymentRepository repository;

    public List<CustomerPayment> getAllPayments() {
        return repository.findAll();
    }

    public CustomerPayment savePayment(CustomerPayment payment) {
        return repository.save(payment);
    }

    public void deletePayment(Long id) {
        repository.deleteById(id);
    }

    public CustomerPayment getById(Long id) {
        return repository.findById(id).orElse(null);
    }

    public List<CustomerPayment> searchByCustomerName(String name) {
        return repository.findByCustomerNameContainingIgnoreCase(name);
    }



    public int getTotalWork() {
        return repository.findAll()
                .stream() .mapToInt(p -> Integer.parseInt(p.getWork()))
                .sum();
    }

    public int getTotalPipe() {
        return repository.findAll().stream()
                .map(CustomerPayment::getPipe)
                .filter(pipe -> pipe != null && !pipe.isEmpty())
                .mapToInt(pipe -> {
                    try {
                        return Integer.parseInt(pipe);
                    } catch (NumberFormatException e) {
                        return 0; // or handle it another way
                    }
                })
                .sum();
    }

    public BigDecimal getTotalPaidAtOffice() {
        return repository.findAll().stream()
                .map(CustomerPayment::getPaidAtOffice)
                .reduce(BigDecimal.ZERO, BigDecimal::add);
    }










}

