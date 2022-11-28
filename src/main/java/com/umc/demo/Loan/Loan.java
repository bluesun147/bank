package com.umc.demo.Loan;

import lombok.Getter;
import lombok.NoArgsConstructor;
import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.Entity;
import javax.persistence.Id;
import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.LocalDateTime;

@Getter
@NoArgsConstructor
@Entity
public class Loan {
    @Id
    private int loannumber;
    private String socialnumber;
    private int branchnumber;
    private double amount;
    private LocalDate loandate; // LocalDateTime 하면 시간까지
}