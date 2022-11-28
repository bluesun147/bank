package com.umc.demo.Loan;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

public interface LoanRepository extends JpaRepository<Loan, Integer>{

    // 고객(주민번호로)의 대출 정보 조회
    @Query(value = "select * from loan where socialNumber = :socialNumber", nativeQuery = true)
    public List<Loan> getUsersLoan(@Param("socialNumber") String socialNumber);

    // 특정 대출의 남은 amount 조회
    @Query(value = "select amount from loan where loanNumber = :loanNumber", nativeQuery = true)
    public double getLoanAmount(@Param("loanNumber") int loanNumber);

    // 특정 대출의 대출 상환
    @Transactional
    @Modifying
    @Query(value = "update loan set amount = :result where loanNumber = :loanNumber", nativeQuery = true)
    public void repayment(
            @Param("result") double result,
            @Param("loanNumber") double loanNumber);
}