package com.umc.demo.Branch;

import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.Entity;
import javax.persistence.Id;
import java.math.BigInteger;
import java.time.LocalDateTime;

@Getter
@NoArgsConstructor
@Entity
public class Branch {
    @Id
    private int branchnumber;
    private String branchname;
    private String branchcity;
}