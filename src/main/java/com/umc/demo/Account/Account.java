package com.umc.demo.Account;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;


import javax.persistence.*;
import java.time.LocalDate;
import java.time.LocalDateTime;

@Getter @Setter
@NoArgsConstructor
@Entity
public class Account {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int accountnumber; // accountNumber라고 하면 account_number를 찾음
    private String socialnumber;
    private int branchnumber;
    private String type;
    private double balance;
    private boolean cardappstatus;
    private LocalDate opendate;
}