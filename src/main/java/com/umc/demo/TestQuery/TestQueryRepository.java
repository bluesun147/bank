package com.umc.demo.TestQuery;

import com.umc.demo.Customer.Customer;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface TestQueryRepository extends JpaRepository<Customer, String> {
    // 테스트 쿼리1
    @Query(value = "select name, type, balance, openDate\n" +
            "from account as a\n" +
            "inner join customer as c\n" +
            "    on a.socialNumber = c.socialNumber\n" +
            "    where c.name = '선민우';", nativeQuery = true)
    public List<Object> test1();

    // 테스트 쿼리2
    @Query(value = "select name, type, balance, openDate\n" +
            "from account as a\n" +
            "inner join customer as c\n" +
            "    on a.socialNumber = c.socialNumber\n" +
            "    where c.name = '선민우'\n" +
            "order by openDate;", nativeQuery = true)
    public List<Object> test2();

    // 테스트 쿼리3
    @Query(value = "select name, a.type, a.balance, t.transactionAmount, t.transactionDate\n" +
            "from account a\n" +
            "inner join customer c\n" +
            "    on a.socialNumber = c.socialNumber\n" +
            "inner join transaction t\n" +
            "    on a.accountNumber = t.accountNumber\n" +
            "where c.name = '선민우' and a.accountNumber = 1 and transactionDate > date_add(now(), interval -1 month);", nativeQuery = true)
    public List<Object> test3();

    // 테스트 쿼리4
    @Query(value = "select C.name, CC.type, a.accountNumber, sum(transactionAmount) totalAmount, CC.cardLimit\n" +
            "from account a\n" +
            "inner join customer C\n" +
            "    on a.socialNumber = C.socialNumber\n" +
            "inner join transaction TD\n" +
            "    on a.accountNumber = TD.accountNumber\n" +
            "inner join credit_card CC on a.accountNumber = CC.accountNumber\n" +
            "where C.name = '선민우'\n" +
            "group by CC.type;", nativeQuery = true)
    public List<Object> test4();
}
