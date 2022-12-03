package com.umc.demo.Customer;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.Date;
import java.util.List;

public interface CustomerRepository extends JpaRepository<Customer, String>{
 // https://kitty-geno.tistory.com/118
    // 고객 등록
    @Query(value = "insert into Customer (socialNumber, name, address, birthdate, email, phoneNumber, job) values (:socialNumber, :name, :address, :birthdate, :email, :phoneNumber, :job)", nativeQuery = true)
    public List<Object> saveCustomer(@Param("socialNumber") String socialNumber, String name, String address, Date birthdate, String email, String phoneNumber, String job);

    // 전체 고객 조회
    @Query(value = "select * from customer", nativeQuery = true)
    public List<Customer> getAllCustomers(); // 왜 customer로 했을 때 안되는거야 date 때문?

    // 이름으로 고객 정보 조회
    @Query(value = "select * from customer where name = :name", nativeQuery = true)
    public List<Customer> getInfoByName(@Param("name") String name);

    // 전체 고객 생일 조회
    @Query(value = "select birthDate from customer", nativeQuery = true)
    public List<Date> getAllCustomersBirthday(); // 타입 틀리면 (Integer로 했을 시)No converter found capable of converting from type [java.sql.Date] to type [@org.springframework.data.jpa.repository.Query java.lang.Integer]

}