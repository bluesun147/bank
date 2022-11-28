package com.umc.demo.Customer;

import lombok.Getter;
import lombok.NoArgsConstructor;
import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.Entity;
import javax.persistence.Id;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.Date;

@Getter
@NoArgsConstructor
@Entity
public class Customer {
    @Id
    private String socialnumber;
    private String name;
    private String address;

    /*// https://binarywoo.tistory.com/97
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private Date birthdate;*/
    private LocalDate birthdate;
    private String email;
    private String phonenumber;
    private String job;

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
