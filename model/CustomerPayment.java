package com.sd.gmbls.model;

import jakarta.persistence.*;
import lombok.*;
import org.springframework.format.annotation.DateTimeFormat;

import java.math.BigDecimal;
import java.time.LocalDate;

@Entity
@Table(name = "customer_payments")
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class CustomerPayment {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String customerName;
    private String village;
    private String phoneNumber;
    private String machineUsed;
    private String work;
    private String pipe;


    private BigDecimal totalAmount;
    private BigDecimal paidAtMachine;
    private BigDecimal paidAtOffice;
    @DateTimeFormat(pattern = "dd-MM-yyyy")
    private LocalDate paymentDate;
    private BigDecimal remainingAmount;
}
