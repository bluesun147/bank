package com.umc.demo.CreditCard;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface CreditCardRepository extends JpaRepository<CreditCard, Integer> {

    // 고객의 모든 카드 조회 (주민번호로)
    @Query(value = "select * from credit_card where socialNumber = :socialNumber", nativeQuery = true)
    public List<CreditCard> getOnesAllCards(@Param("socialNumber") String socialNumber);

    // 카드 번호로 연결 계좌 잔고 조회
    @Query(value = "select A.balance from account as A where accountNumber in (select accountNumber from credit_card where cardNumber = :cardNumber)", nativeQuery = true)
    public double getAccountBalance(@Param("cardNumber") int cardNumber);

    // 카드 한도 조회
    @Query(value = "select cardLimit from credit_card where cardNumber = :cardNumber", nativeQuery = true)
    public double getLimit(@Param("cardNumber") int cardNumber);

    // 계좌에 연결된 카드 조회
    @Query(value = "select * from credit_card where accountnumber = :accountNumber", nativeQuery = true)
    public List<CreditCard> getCards(@Param("accountNumber") int accountNumber);
}