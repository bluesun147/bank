package com.umc.demo.Transaction;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import javax.persistence.*;
import java.time.LocalDateTime;

@Getter
@Setter
@NoArgsConstructor
@EntityListeners(AuditingEntityListener.class)
@Entity
public class Transaction {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int transnumber;
    private int accountnumber;
    private double transactionamount;
    @CreatedDate
    private LocalDateTime transactiondate;
}