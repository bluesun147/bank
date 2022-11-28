package com.umc.demo.CreditCard;

import lombok.Getter;
import lombok.NoArgsConstructor;
import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.Entity;
import javax.persistence.Id;
import java.time.LocalDateTime;
import java.util.Date;

@Getter
@NoArgsConstructor
@Entity
public class CreditCard {
    @Id
    private int cardnumber; // cardNumber 이런식으로 쓰면 card_number를 찾음, 다 소문자로 써야 함.
    private String socialnumber;
    private int accountnumber;
    private String type;
    private double cardlimit;
    private LocalDateTime applicationdate;
}
