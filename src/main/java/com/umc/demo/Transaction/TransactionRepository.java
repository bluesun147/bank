package com.umc.demo.Transaction;

import com.umc.demo.Loan.Loan;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

public interface TransactionRepository extends JpaRepository<Transaction, Integer>{
    // 특정 계좌 거래 내역 조회 (누구한테 보낸지는 말고 입금, 출금만 기록)
    @Query(value = "select * from Transaction where accountNumber = :accountNumber", nativeQuery = true)
    public List<Transaction> getAccountTrans(@Param("accountNumber") int accountNumber);

    // 거래 내역 작성
    @Transactional
    @Modifying
    @Query(value = "insert into Transaction values(:accountNumber, :transactionAmount)", nativeQuery = true)
    public void putTrans(
            @Param("accountNumber") int accountNumber,
            @Param("transactionAmount") double money);
}