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
@EntityListeners(AuditingEntityListener.class)
@Entity
public class Account {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int accountnumber; // accountNumber라고 하면 account_number를 찾음
    private String socialnumber;
    private int branchnumber;
    private String type;
    private double balance;
    @CreatedDate
    private boolean cardappstatus;
    private LocalDate opendate;

//    @Builder
//    public Customer(int user_id, String email, String password, String name, String profile_img, String text) {
//        this.user_id = user_id;
//        this.email = email;
//        this.password = password;
//        this.name = name;
//        this.profile_img = profile_img;
//        this.text = text;
//    }
}
