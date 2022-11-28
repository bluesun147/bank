package com.umc.demo.Account;

import com.umc.demo.Customer.Customer;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;
import java.util.List;

// <Account, Integer> 부분에 Account 안쓰면 List<Account>하면 오류나서 List<Object> 이런식으로 썼음
public interface AccountRepository extends JpaRepository<Account, Integer> {
    // https://kitty-geno.tistory.com/118
    // 계좌 등록
    @Query(value = "insert into account (socialnumber, branchnumber, type, balance, cardappstatus) values (:socialNumber, :branchNumber, :type, :balance, :cardAppStatus)", nativeQuery = true)
    public List<Customer> createAccount(
            @Param("socialNumber") String socialNumber,// accountNumber라고 하면 account_number를 찾음
            @Param("branchNumber") int branchNumber,
            @Param("type") String type,
            @Param("balance") double balance,
            @Param("cardAppStatus") boolean cardAppStatus
    );

    // 모든 계좌 조회
    @Query(value = "select * from account", nativeQuery = true)
    public List<Account> getAllAccounts();

    // 특정 사용자 계좌 조회
    @Query(value = "select * from account where socialNumber = :socialNumber", nativeQuery = true)
    public List<Account> getAccount(@Param("socialNumber") String socialNumber);

    // 특정 계좌 잔고 조회
    @Query(value = "select balance from account where accountNumber = :accountNumber", nativeQuery = true)
    public Double getAccountBalance(@Param("accountNumber") int accountNumber);// 특정 사용자 계좌 조회();


    // 입금 https://kitty-geno.tistory.com/118
    @Transactional
    @Modifying // 이거 붙여야 함.
    @Query(value = "update account set balance = :result where accountNumber = :accountNumber", nativeQuery = true)
    public void deposit(@Param("result") double result, // void로 해도됨. List<Object>하면 오류
                        @Param("accountNumber") int accountNumber);

    // 출금
    @Transactional
    @Modifying
    @Query(value = "update account set balance = :result where accountNumber = :accountNumber", nativeQuery = true)
    public void withdraw(@Param("result") double result,
                         @Param("accountNumber") int accountNumber);

    // 특정 브랜치에서 개설된 계좌 조회
    @Query(value = "select * from account where branchNumber = :branchNumber", nativeQuery = true)
    public List<Account> getBranchAccounts(@Param("branchNumber") int branchNumber);
}