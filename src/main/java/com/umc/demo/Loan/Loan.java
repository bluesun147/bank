package com.umc.demo.Loan;

import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.Entity;
import javax.persistence.Id;
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
    private LocalDateTime loandate;
}