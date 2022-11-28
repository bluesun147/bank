package com.umc.demo.CreditCard;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import java.time.LocalDate;

@Getter @Setter
@NoArgsConstructor
@Entity
public class CreditCard {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int cardnumber; // cardNumber 이런식으로 쓰면 card_number를 찾음, 다 소문자로 써야 함.
    private String socialnumber;
    private int accountnumber;
    private String type;
    private double cardlimit;
    private LocalDate applicationdate;
}