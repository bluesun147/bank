package com.umc.demo.Customer;

import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.Date;
import java.util.List;

@RequiredArgsConstructor
//@RestController
@Controller
@RequestMapping("/customer")
public class CustomerController {

    @Autowired
    CustomerRepository customerRepository;


    //회원가입 폼(완료)
    @GetMapping("/joinForm")
    public String joinForm(){
        return "customer/joinForm";
    }



    //오류 Date birthdate
    @PostMapping("/join")
    public String saveCustomer(String socialNumber, String name, String address, Date birthdate, String email, String phoneNumber, String job) {
        customerRepository.saveCustomer(socialNumber, name, address, birthdate, email, phoneNumber, job);
        return "customer/all";
    }


    // 전체 고객 조회(완료)
    @GetMapping("/all")
    public String getPosts(Model model) {
        model.addAttribute("customers", customerRepository.getAllCustomers());
        return "customer/all";
    }

    //이름으로 고객 정보 조회 폼(완료)
    @GetMapping("/nameForm")
    public String nameForm(){
        return "customer/nameForm";
    }

    // 이름으로 고객 정보 조회(완료)
    @PostMapping("/name")
    public String getInfoByName(Model model, @RequestParam("name") String name) {
        model.addAttribute("customersByName", customerRepository.getInfoByName(name));
        return "customer/name";
    }



    // 전체 고객 생일 조회(완료)
    @GetMapping("/all/birthday")
    public String getPosts2(Model model) {
        model.addAttribute("customers", customerRepository.getAllCustomers());
        return "customer/birthday";
    }

    // 테스트 쿼리1
    @GetMapping("/test")
    public List<Object> test() { // list 타입 Customer로 안하고 그냥 Object해도 결고 나옴.
        return customerRepository.test();
    }
}