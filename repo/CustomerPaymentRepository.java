package com.sd.gmbls.repo;

import com.sd.gmbls.model.CustomerPayment;


import org.springframework.data.jpa.repository.JpaRepository;
import java.util.List;

public interface CustomerPaymentRepository extends JpaRepository<CustomerPayment, Long> {
    List<CustomerPayment> findByCustomerNameContainingIgnoreCase(String customerName);

}


